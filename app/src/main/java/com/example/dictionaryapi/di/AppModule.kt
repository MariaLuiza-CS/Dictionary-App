package com.example.dictionaryapi

import android.app.Application
import androidx.room.Room
import com.example.dictionaryapi.data.dao.WordDao
import com.example.dictionaryapi.data.local.AppDataBase
import com.example.dictionaryapi.data.remote.WordApi
import com.example.dictionaryapi.data.repository.DataBaseRepository
import com.example.dictionaryapi.data.repository.WordRepository
import com.example.dictionaryapi.data.repositoryimpl.DataBaseRepositoryImpl
import com.example.dictionaryapi.data.repositoryimpl.WordRepositoryImpl
import com.example.dictionaryapi.domain.usecases.*
import com.example.dictionaryapi.domain.utils.Constant
import com.example.dictionaryapi.presentation.viewmodels.FavoriteViewModel
import com.example.dictionaryapi.presentation.viewmodels.HistoryViewModel
import com.example.dictionaryapi.presentation.viewmodels.WordListViewModel
import com.example.dictionaryapi.presentation.viewmodels.WordViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    fun provideDataBase(application: Application): AppDataBase {
        return Room.databaseBuilder(
            application,
            AppDataBase::class.java,
            Constant.DATA_BASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: AppDataBase): WordDao = dataBase.wordDao

    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    single { provideHttpClient() }
    single { provideRetrofit() }

    fun provideWordApi(retrofit: Retrofit): WordApi {
        return retrofit.create(WordApi::class.java)
    }

    single { provideWordApi(get()) }

    factory { GetAllWordsUseCase(get()) }
    factory { InsertWordUseCase(get()) }
    factory { SetFavoriteUseCase(get()) }
    factory { GetWordUseCase(get()) }
    factory { GetFavoriteWordsUseCase(get()) }

    viewModel { WordListViewModel(get(), get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { WordViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get()) }

    single<WordRepository> { WordRepositoryImpl(get()) }
    single<DataBaseRepository> { DataBaseRepositoryImpl(get()) }
}