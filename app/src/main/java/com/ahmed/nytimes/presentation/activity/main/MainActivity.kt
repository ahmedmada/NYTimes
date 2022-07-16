package com.ahmed.nytimes.presentation.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmed.nytimes.common.Dialog.Companion.showMessageDialog
import com.ahmed.nytimes.databinding.ActivityMainBinding
import com.ahmed.nytimes.presentation.adapter.ArticlesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var articlesAdapter: ArticlesAdapter? = null
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAdapter()
        fetchArticlesList()
        observeArticlesInfo()
    }

    private fun initAdapter() {
        articlesAdapter = ArticlesAdapter(arrayListOf(), this)
        binding.articlesListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articlesAdapter
        }
    }

    private fun fetchArticlesList() {
        CoroutineScope(Dispatchers.Main).launch {
            mainActivityViewModel.fetchArticlesList()
        }
    }

    private fun observeArticlesInfo() {
        mainActivityViewModel.articlesLiveData.observe(this) { response ->
            when {
                response.isLoading!! -> {
                    binding.loadingProgressBar.visibility = View.VISIBLE
                }
                response.isError!! -> {
                    binding.loadingProgressBar.visibility = View.GONE
                    showMessageDialog(this,response.errorMessage!!)
                }
                else -> {
                    binding.loadingProgressBar.visibility = View.GONE

                    response?.let { articles ->
                        articles.let {
                            articlesAdapter?.refreshAdapter(articles.articles.results)
                        }
                    }
                }
            }
        }
    }
}