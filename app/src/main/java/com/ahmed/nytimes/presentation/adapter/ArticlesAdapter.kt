package com.ahmed.nytimes.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.nytimes.R
import com.ahmed.nytimes.databinding.ItemArticleListBinding
import com.ahmed.nytimes.network.model.Results
import com.bumptech.glide.Glide

class ArticlesAdapter(var articles: ArrayList<Results>, var context: Context) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder =
        ArticlesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article_list, parent, false)
        )

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) =
        holder.bind(articles[position])

    @SuppressLint("NotifyDataSetChanged")
    fun refreshAdapter(newArticles: ArrayList<Results>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }

    inner class ArticlesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemArticleListBinding.bind(view)

        private val articalDesc = binding.articalDesc
        private val type = binding.type
        private val auther = binding.auther
        private val date = binding.date
        private val image = binding.imageView

        fun bind(article: Results) {
            articalDesc.text = article.abstract
            type.text = article.type
            auther.text = article.byline
            date.text = article.publishedDate
            if (article.media.size > 0 && article.media[0].mediaMetadatas.size > 0)
                Glide.with(context).load(article.media[0]?.mediaMetadatas[0]?.url)
                    .placeholder(R.drawable.ic_baseline_image_24).into(image)
            else
                Glide.with(context).load(R.drawable.ic_baseline_image_24).into(image)
        }
    }
}