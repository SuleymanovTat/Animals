package ru.suleymanovtat.list.animals.presentation.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import io.reactivex.exceptions.CompositeException
import retrofit2.HttpException
import ru.suleymanovtat.list.animals.App
import ru.suleymanovtat.list.animals.R
import java.net.UnknownHostException

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private fun getContext() = getApplication<App>()
    private fun getString(@StringRes id: Int): String = getContext().getString(id)
    protected fun onError(throwable: Throwable): String {
        var localThrowable = throwable
        if (localThrowable is CompositeException) {
            localThrowable = localThrowable.exceptions.first()
        }
        return when (localThrowable) {
            is UnknownHostException -> getString(R.string.no_internet_connection)
            is HttpException -> when {
                localThrowable.code() >= 500 -> getString(R.string.server_error)
                localThrowable.code() == 400 -> getString(R.string.unknown_error_has_occurred)
                else -> getString(R.string.unknown_error_has_occurred)
            }
            else -> getString(R.string.unknown_error_has_occurred)
        }
    }
}