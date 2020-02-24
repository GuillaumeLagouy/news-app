package com.glagouy.news.services

import com.glagouy.news.dataclasses.Article
import com.glagouy.news.dataclasses.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("everything")
    fun getArticles(@Query("apiKey") apikey:String, @Query("q") word:String): Call<ArticleResponse>
}