package com.ahmed.nytimes.presentation.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ahmed.nytimes.network.model.state.ArticlesState
import com.ahmed.nytimes.network.use_case.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val articleUseCase: ArticleUseCase
) : ViewModel() {

    private val _articlesMutableLiveDate = articleUseCase.articlesLiveData
    val articlesLiveData: LiveData<ArticlesState> = _articlesMutableLiveDate

    suspend fun fetchArticlesList() {
        articleUseCase.fetchArticlesList()
    }
}