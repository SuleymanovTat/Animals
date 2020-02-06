package ru.suleymanovtat.list.animals.repository

import ru.suleymanovtat.list.animals.model.Animals
import ru.suleymanovtat.list.animals.repository.network.ApiService


class AnimalsRepositoryImpl(private val catApi: ApiService) : AnimalsRepository {

    override suspend fun loading(animail: String): UseCaseResult<List<Animals>> {
        return try {
            val result = catApi.getAnimails(animail).data!!
            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}

sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
}