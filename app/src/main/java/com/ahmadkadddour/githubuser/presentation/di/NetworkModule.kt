package com.ahmadkadddour.githubuser.presentation.di

import com.ahmadkadddour.githubuser.BuildConfig
import com.ahmadkadddour.githubuser.data.exception.factory.ExceptionFactory
import com.ahmadkadddour.githubuser.data.provider.remote.ApiProvider
import com.ahmadkadddour.githubuser.data.provider.remote.IApiProvider
import com.ahmadkadddour.githubuser.data.sorce.remote.api.service.RetrofitService
import com.ahmadkadddour.githubuser.data.util.network.networkchecker.NetworkChecker
import com.ahmadkadddour.githubuser.data.util.network.networkchecker.NetworkCheckerImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideNetworkChecker(checker: NetworkCheckerImpl): NetworkChecker {
        return checker
    }

    @Provides
    @Singleton
    fun providerApiProvider(
        networkChecker: NetworkChecker,
        exceptionFactory: ExceptionFactory
    ): IApiProvider {
        return ApiProvider(networkChecker, exceptionFactory)
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
            connectTimeout(1, TimeUnit.MINUTES)
            readTimeout(1, TimeUnit.MINUTES)
            writeTimeout(1, TimeUnit.MINUTES)
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.GITHUB_BASE_URL)
            client(client)
            addConverterFactory(
                MoshiConverterFactory.create(moshi)
            )
        }.build()
    }

    @Provides
    @Singleton
    fun providerRetrofitService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }
}