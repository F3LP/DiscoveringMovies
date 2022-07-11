package com.facd.discoveringmovies.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideOkHttpClient(get()) }
    factory { provideImdbApi(get()) }
    factory { provideLoggingInterceptor() }
    single { provideRetrofit(get()) }

}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
}

fun provideImdbApi(retrofit: Retrofit): ApiImdbInterface =
    retrofit.create(ApiImdbInterface::class.java)

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://imdb-api.com").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}