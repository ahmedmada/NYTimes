package com.ahmed.nytimes.network.model.state

import com.ahmed.nytimes.network.model.Articles

data class ArticlesState(
    var isError: Boolean? = false,
    var isLoading: Boolean? = true,
    var errorMessage: String? = "",
    var articles: Articles = Articles()
)