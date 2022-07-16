package com.ahmed.nytimes.network

import com.ahmed.nytimes.network.model.Articles
import retrofit2.Response
import retrofit2.http.GET

interface ArticlesService {
    @GET("svc/mostpopular/v2/emailed/7.json?api-key=9ycVsveNvxNdTaxkGzy7i8BBJpwJKXBy")
    suspend fun fetchArticles(): Response<Articles>
}