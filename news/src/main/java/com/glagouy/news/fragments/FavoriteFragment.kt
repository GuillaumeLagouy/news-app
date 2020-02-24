package com.glagouy.news.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.glagouy.news.R
import com.glagouy.news.adapters.ArticleAdapter
import com.glagouy.news.dataclasses.Article
import com.glagouy.news.repositories.ArticleRepository
import com.glagouy.news.viewmodels.ArticlesViewModel
import kotlinx.android.synthetic.main.articles_list_fragment.*

class FavoriteFragment : Fragment() {
    lateinit var articlesViewModel: ArticlesViewModel
    val handler = object : ArticleSelected {
        override fun onSelected(article: Article) {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.articlesFragmentContainer, ArticleDetailFragment.newInstance(article))
                addToBackStack(null)
            }?.commit()
        }
    }
    val adapter = ArticleAdapter(handler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articlesViewModel = ViewModelProvider(activity!!).get(ArticlesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.articles_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleRecycleView.layoutManager = LinearLayoutManager(context)
        articleRecycleView.adapter = adapter


        articlesViewModel.listArticles.observe(viewLifecycleOwner, Observer {
            val fav = it.filter { article -> article.isFavorite }
            adapter.updateData(fav)
        })
    }
}