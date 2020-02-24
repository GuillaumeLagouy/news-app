package com.glagouy.news.datasources

import com.glagouy.news.BuildConfig
import com.glagouy.news.dataclasses.Article
import com.glagouy.news.services.ArticleService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource {
    private val service: ArticleService
    private val apiKey: String = "235cb3e0539d402d9f4e341ed255ffec"

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(client)
            baseUrl("https://newsapi.org/v2/")
        }.build()
        service = retrofit.create(ArticleService::class.java)
    }

    fun getRemoteArticles(): List<Article> {
        val result = service.getArticles(apikey = apiKey, word = "sculpture").execute()
        return if(result.isSuccessful) {
            result.body()?.articles ?: emptyList()
        }else {
            emptyList()
        }
    }
}