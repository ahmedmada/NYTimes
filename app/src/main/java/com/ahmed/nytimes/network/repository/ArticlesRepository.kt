package com.ahmed.nytimes.network.repository

import com.ahmed.nytimes.network.ArticlesService
import com.ahmed.nytimes.network.model.Articles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val apiServiceAPI: ArticlesService
    ) {

    suspend fun fetchArticles() : Flow<Response<Articles>> {
        return  flow {
            val articlesInfo = apiServiceAPI.fetchArticles()
            emit(articlesInfo)
        }
    }
}