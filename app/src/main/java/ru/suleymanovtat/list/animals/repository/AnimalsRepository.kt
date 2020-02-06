package ru.suleymanovtat.list.animals.repository

import ru.suleymanovtat.list.animals.model.Animals

interface AnimalsRepository {
    suspend fun loading(animail: String): UseCaseResult<List<Animals>>
}