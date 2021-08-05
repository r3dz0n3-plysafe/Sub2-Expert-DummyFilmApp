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
import okhttp3.CertificatePinner
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
        // TODO 2.3 : Menerapkan certificate pinning untuk koneksi ke server.
        val hostname = "*.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
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