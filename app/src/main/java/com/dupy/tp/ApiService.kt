package com.dupy.tp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class JokeResponse(
    val error: Boolean,
    val category: String,
    val type: String,
    val setup: String
)

interface ApiService {
    @GET("joke/Any?lang=fr")
    fun getRandomJoke(): Call<JokeResponse>

    companion object {
        private const val BASE_URL = "https://v2.jokeapi.dev/"

        fun create(): ApiService {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
