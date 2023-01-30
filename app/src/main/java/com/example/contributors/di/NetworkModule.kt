package com.example.contributors.di

import com.example.contributors.common.utils.Constans.Companion.BASE_URL
import com.example.contributors.common.utils.Constans.Companion.BASE_URLTWO
import com.example.contributors.services.Api
import com.example.contributors.services.ApiTwo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("first_retrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    @Named("second_retrofit")
    fun provideRetrofitTwo(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URLTWO)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun getRetrofitServiceInstance(@Named("first_retrofit") retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInstanceTwo(@Named("second_retrofit") retrofit: Retrofit): ApiTwo {
        return retrofit.create(ApiTwo::class.java)
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingIntercepter: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingIntercepter)
            .build()

    @Provides
    @Singleton
    fun provideGson() = Gson()
}
