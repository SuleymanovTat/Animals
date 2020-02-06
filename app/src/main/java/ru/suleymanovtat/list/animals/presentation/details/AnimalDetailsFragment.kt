package ru.suleymanovtat.list.animals.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.deatils_animals_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.suleymanovtat.list.animals.R
import ru.suleymanovtat.list.animals.model.Animals
import ru.suleymanovtat.list.animals.presentation.animals.AnimalsFragment.Companion.KEY_ANIMAL

class AnimalDetailsFragment : Fragment(R.layout.deatils_animals_fragment) {


    companion object {
        fun newInstance(animals: Animals): AnimalDetailsFragment {
            val bundle = Bundle()
            bundle.putParcelable(KEY_ANIMAL, animals)
            val fragment = AnimalDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel: AnimalDetailsViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val animals = arguments!!.getParcelable<Animals>(KEY_ANIMAL)
        viewModel.setAnimals(animals)
        viewModel.mutableLiveData.observe(viewLifecycleOwner, Observer { animals ->
            textViewTitle.text = animals.title
            Picasso.get()
                .load(animals.url)
                .resize(900, 600)
                .centerCrop()
                .into(imageViewPhoto)
        })
    }
}
