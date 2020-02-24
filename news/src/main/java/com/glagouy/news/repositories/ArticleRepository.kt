package com.glagouy.news.repositories

import com.glagouy.news.dataclasses.Article
import com.glagouy.news.datasources.RemoteDataSource

class ArticleRepository {
    private val online = RemoteDataSource()

    fun getArticles(): List<Article> {
        return online.getRemoteArticles()
    }
}