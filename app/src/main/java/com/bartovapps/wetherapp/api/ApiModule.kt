package com.bartovapps.wetherapp.api

import android.util.Log
import android.util.Log.i
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by motibartov on 14/01/2018.
 */

@Module
class ApiModule{
    companion object {
        val TAG = "ApiModule"
        val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        val BASE_ICON_URL = "http://openweathermap.org/img/w/"
        val APPID = "b2eae64e8afadeb8071560de94288556"
    }


    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient{
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(logInterceptor)
                .addInterceptor{chain ->
                    var request = chain.request()
                    val url =request.url().
                            newBuilder().
                            addQueryParameter("APPID", APPID).build()
                    i(TAG, "Request url: $url")

                    request = request.newBuilder().url(url).build()
                    chain.proceed(request)
                }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit{
        return Retrofit.Builder().
                baseUrl(BASE_URL).
                client(client).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }




}