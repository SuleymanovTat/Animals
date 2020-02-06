package ru.suleymanovtat.list.animals.presentation.animals

import ru.suleymanovtat.list.animals.model.Animals

interface OnAnimalsClickListener {
    fun onAnimalsClick(animals: Animals)
}