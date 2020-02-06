package ru.suleymanovtat.list.animals.presentation.animals

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.suleymanovtat.list.animals.model.Animals
import ru.suleymanovtat.list.animals.presentation.base.BaseViewModel
import ru.suleymanovtat.list.animals.repository.AnimalsRepository
import ru.suleymanovtat.list.animals.repository.UseCaseResult

class AnimalsViewModel(private val animalsRepository: AnimalsRepository, application: Application) :
    BaseViewModel(application) {

    val animals = MutableLiveData<List<Animals>>()
    val showError = MutableLiveData<String>()

    fun loading(animal: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = animalsRepository.loading(animal)) {
                is UseCaseResult.Success -> animals.postValue(result.data)
                is UseCaseResult.Error -> showError.postValue(onError(result.exception))
            }
        }
    }
}
