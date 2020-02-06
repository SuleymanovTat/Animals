package ru.suleymanovtat.list.animals.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.suleymanovtat.list.animals.model.Animals

class AnimalDetailsViewModel : ViewModel() {


    val mutableLiveData = MutableLiveData<Animals>()

    fun setAnimals(animals: Animals?) {
        mutableLiveData.postValue(animals)
    }
}
