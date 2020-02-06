package ru.suleymanovtat.list.animals.presentation.animals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_animails.view.*
import ru.suleymanovtat.list.animals.R
import ru.suleymanovtat.list.animals.model.Animals

class AnimalsAdapter(
    private var animals: List<Animals>,
    private val listener: OnAnimalsClickListener
) : RecyclerView.Adapter<AnimalsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_animails, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = animals[position]
        with(holder.mView) {
            textViewTitle.text = item.title
            Picasso.get()
                .load(item.url)
                .resize(200, 200)
                .centerCrop()
                .into(imageViewPhoto)
            setOnClickListener {
                listener.onAnimalsClick(item)
            }
        }
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
}