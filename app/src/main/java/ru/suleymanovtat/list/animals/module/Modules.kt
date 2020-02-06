package ru.suleymanovtat.list.animals.module

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.suleymanovtat.list.animals.presentation.animals.AnimalsViewModel
import ru.suleymanovtat.list.animals.presentation.details.AnimalDetailsViewModel
import ru.suleymanovtat.list.animals.repository.AnimalsRepository
import ru.suleymanovtat.list.animals.repository.AnimalsRepositoryImpl
import ru.suleymanovtat.list.animals.repository.network.ApiService
import java.util.concurrent.TimeUnit

const val API_BASE_URL = "http://kot3.com/"

val appModules = module {

    single {
        createWebService<ApiService>(
            okHttpClient = createHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = API_BASE_URL
        )
    }

    single<AnimalsRepository> { AnimalsRepositoryImpl(catApi = get()) }

    viewModel { AnimalsViewModel(animalsRepository = get(), application = androidApplication()) }
    viewModel { AnimalDetailsViewModel() }
}

fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)

    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    factory: CallAdapter.Factory,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}