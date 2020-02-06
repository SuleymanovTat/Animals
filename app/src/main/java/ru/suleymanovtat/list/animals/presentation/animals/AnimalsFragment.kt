package ru.suleymanovtat.list.animals.presentation.animals

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.animails_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.suleymanovtat.list.animals.R
import ru.suleymanovtat.list.animals.model.Animals
import ru.suleymanovtat.list.animals.presentation.details.AnimalDetailsFragment

class AnimalsFragment : Fragment(R.layout.animails_fragment),
    OnAnimalsClickListener {

    private val viewModel: AnimalsViewModel by viewModel()

    companion object {

        const val KEY_ANIMAL = "animal"

        fun newInstance(text: String): AnimalsFragment {
            val bundle = Bundle()
            bundle.putString(KEY_ANIMAL, text)
            val fragment = AnimalsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonUpdate.setOnClickListener { viewModel.loading(arguments!!.getString(KEY_ANIMAL)!!) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null)
            viewModel.loading(arguments!!.getString(KEY_ANIMAL)!!)
        viewModel.animals.observe(viewLifecycleOwner, Observer { list ->
            val animalsAdapter = AnimalsAdapter(list, this)
            recyclerViewAnimails.adapter = animalsAdapter
            recyclerViewAnimails.visibility = View.VISIBLE
            textViewMessage.visibility = View.GONE
            buttonUpdate.visibility = View.GONE
        })
        viewModel.showError.observe(viewLifecycleOwner, Observer { meassage ->
            recyclerViewAnimails.visibility = View.GONE
            textViewMessage.visibility = View.VISIBLE
            buttonUpdate.visibility = View.VISIBLE
            textViewMessage.text = meassage
        })
    }

    override fun onAnimalsClick(animals: Animals) {
        activity?.supportFragmentManager!!.beginTransaction()
            .hide(activity?.supportFragmentManager!!.findFragmentById(R.id.constraintLayout)!!)
            .add(R.id.constraintLayout, AnimalDetailsFragment.newInstance(animals))
            .addToBackStack(null)
            .commit()
    }
}


