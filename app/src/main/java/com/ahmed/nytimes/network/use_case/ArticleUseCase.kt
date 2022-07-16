package com.ahmed.nytimes.network.use_case

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmed.nytimes.network.model.state.ArticlesState
import com.ahmed.nytimes.network.repository.ArticlesRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ArticleUseCase @Inject constructor(private val articlesRepository: ArticlesRepository) {
    private val _articlesMutableLiveDate = MutableLiveData<ArticlesState>()
    val articlesLiveData: LiveData<ArticlesState> = _articlesMutableLiveDate

    suspend fun fetchArticlesList() {
        _articlesMutableLiveDate.value = ArticlesState(
            isLoading = true,
        )
        try {
            articlesRepository.fetchArticles().collect { response ->
                if (response.isSuccessful) {
                    val articlesInfo = response.body()
                    articlesInfo?.let { model ->
                        _articlesMutableLiveDate.value = ArticlesState(
                            isError = false,
                            isLoading = false,
                            errorMessage = "",
                            articles = model
                        )
                    }

                } else
                    _articlesMutableLiveDate.value = ArticlesState(
                        isError = true,
                        isLoading = false,
                        errorMessage = response.message()
                    )
            }

        } catch (e: Exception) {
            _articlesMutableLiveDate.value = ArticlesState(
                isError = true,
                isLoading = false,
                errorMessage = e.message
            )
        }
    }
}