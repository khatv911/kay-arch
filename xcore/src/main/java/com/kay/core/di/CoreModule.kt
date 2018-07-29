package com.kay.core.di

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import com.kay.core.utils.AccessTokenInterceptor
import com.kay.core.utils.OffsetDateTimeJsonAdapter
import com.kay.core.utils.PrefHelper
import com.kay.core.viewmodel.ViewModelFactory
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.OffsetDateTime
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

/**
 * Created by Kay Tran on 2/2/18.
 * Profile: https://github.com/khatv911
 * Email: khatv911@gmail.com
 */
@Module(includes = [CoreModule.VMFactory::class])
class CoreModule {

    @Provides
    @Singleton
    fun provideSharedPrefs(application: Application): SharedPreferences {
        return PrefHelper.defaultPrefs(application)
    }


    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10L * 1024 * 1024 // 10MB of network cache
        return Cache(application.cacheDir, cacheSize)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, prefs: SharedPreferences): OkHttpClient =
            OkHttpClient.Builder().apply {
                cache(cache)
                addInterceptor(AccessTokenInterceptor(prefs))
                addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                    Timber.tag("OkHttp").d(message)
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
            }.build()


    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(OffsetDateTime::class.java, OffsetDateTimeJsonAdapter())
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
            Retrofit.Builder()
                    .baseUrl("https://google.com")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(okHttpClient)
                    .build()

    @Module
    abstract class VMFactory {
        @Binds
        @Singleton
        abstract fun viewModelProviderFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    }
}