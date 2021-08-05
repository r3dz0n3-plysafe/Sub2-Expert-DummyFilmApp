package com.dicoding.dummyfilmapp.core.di

import androidx.room.Room
import com.dicoding.dummyfilmapp.core.BuildConfig
import com.dicoding.dummyfilmapp.core.data.FilmRepository
import com.dicoding.dummyfilmapp.core.data.source.local.LocalDataSource
import com.dicoding.dummyfilmapp.core.data.source.local.room.FilmDatabase
import com.dicoding.dummyfilmapp.core.data.source.remote.RemoteDataSource
import com.dicoding.dummyfilmapp.core.data.source.remote.network.RetrofitEndPoint
import com.dicoding.dummyfilmapp.core.domain.repository.IFilmRepository
import com.dicoding.dummyfilmapp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<FilmDatabase>().filmDao() }
    single {
        // TODO 2.1 : Menerapkan encryption pada database.
        val passKey = BuildConfig.PASS_KEY
        val passphrase: ByteArray = SQLiteDatabase.getBytes(passKey.toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FilmDatabase::class.java, "film.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RetrofitEndPoint::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IFilmRepository> {
        FilmRepository(
            get(),
            get(),
            get()
        )
    }
}