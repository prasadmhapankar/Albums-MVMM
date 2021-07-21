package com.jpmc.android_challenge.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jpmc.android_challenge.api.ApiConstants
import com.jpmc.android_challenge.api.ApiService
import dagger.Module
import dagger.Provides
import com.jpmc.android_challenge.BuildConfig
import com.jpmc.android_challenge.utils.NetworkHelper
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesGson(): Gson {
        val builder = GsonBuilder()
        return builder.create()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() =
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
            }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache) : OkHttpClient{
        return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true)
                .build()
    }

    @Provides
    fun provideRetrofitForApiService(okHttpClient: OkHttpClient) : ApiService {
        return Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesCache(context: Context): Cache = Cache(context.cacheDir, 10 * 1024 * 1024)

    @Singleton
    @Provides
    fun provideNetworkHelper(context: Context): NetworkHelper = NetworkHelper(context)

}