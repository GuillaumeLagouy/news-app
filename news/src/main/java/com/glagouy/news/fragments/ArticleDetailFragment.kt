package com.glagouy.news.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.glagouy.news.R
import com.glagouy.news.dataclasses.Article
import com.glagouy.news.viewmodels.ArticlesViewModel
import kotlinx.android.synthetic.main.articles_detail_fragment.*

class ArticleDetailFragment : Fragment() {
    private lateinit var viewmodel : ArticlesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
        return inflater.inflate(R.layout.articles_detail_fragment, container, false)
    }

    val article : Article by lazy {
        arguments?.getParcelable<Article>("article")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleLabel.text = article.title
        descriptionLabel.text = article.description
        Glide.with(this).load(article.urlToImage).into(articleImage)

        shareBtn.setOnClickListener{
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, article.url)
            startActivity(Intent.createChooser(shareIntent, "Envoyer le lien de la news"))
        }

        showBtn.setOnClickListener {
            val uris = Uri.parse(article.url)
            val intents = Intent(Intent.ACTION_VIEW, uris)
            val b = Bundle()
            b.putBoolean("new_window", true)
            intents.putExtras(b)
            context?.startActivity(intents)
        }

        favBtn.setOnClickListener {
            println("fav")
            viewmodel.toggleFav(article)
        }
    }

    companion object {
        fun newInstance(article:Article): ArticleDetailFragment {
            return ArticleDetailFragment().apply {
                arguments = bundleOf("article" to article)
            }
        }
    }
}