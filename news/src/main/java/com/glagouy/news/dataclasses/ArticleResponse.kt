package com.glagouy.news.dataclasses

data class ArticleResponse (val status:String, val totalResults:Int, val articles:List<Article>)