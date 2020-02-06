package ru.suleymanovtat.list.animals.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_main.*
import ru.suleymanovtat.list.animals.R
import ru.suleymanovtat.list.animals.presentation.animals.AnimalsFragment


class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
        const val KEY_POSITION = "position"
        const val KEY_CAT = "cat"
        const val KEY_DOG = "dog"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            val cat = AnimalsFragment.newInstance(KEY_CAT)
            val dog = AnimalsFragment.newInstance(KEY_DOG)
            childFragmentManager.beginTransaction()
                .add(R.id.main, cat, KEY_CAT)
                .add(R.id.main, dog, KEY_DOG)
                .hide(dog)
                .commit()
        } else {
            tabLayout.getTabAt(savedInstanceState.getInt(KEY_POSITION))?.select()
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == 0) {
                    showFragment(KEY_CAT, KEY_DOG)
                } else {
                    showFragment(KEY_DOG, KEY_CAT)
                }
            }
        })
    }

    private fun showFragment(keyShow: String, keyHide: String) {
        childFragmentManager.beginTransaction()
            .hide(childFragmentManager.findFragmentByTag(keyHide)!!)
            .show(childFragmentManager.findFragmentByTag(keyShow)!!)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_POSITION, tabLayout?.selectedTabPosition!!)
    }
}


