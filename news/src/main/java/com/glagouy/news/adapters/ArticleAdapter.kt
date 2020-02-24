package com.glagouy.news.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.glagouy.news.R
import com.glagouy.news.dataclasses.Article
import com.glagouy.news.fragments.ArticleSelected
import jp.wasabeef.glide.transformations.BlurTransformation

class ArticleAdapter(private val handler: ArticleSelected) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private val dataset: MutableList<Article> = mutableListOf()

    class ViewHolder(val root: View, val handler: ArticleSelected) :
        RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            val txtTitle = root.findViewById<TextView>(R.id.itemTitleLabel)
            val txtDesc = root.findViewById<TextView>(R.id.itemDescriptionLabel)
            val imageView = root.findViewById<ImageView>(R.id.itemImage)
            txtTitle.text = item.title
            txtDesc.text = item.description
            Glide.with(root)
                .load(item.urlToImage)
                .apply(bitmapTransform(BlurTransformation(22)))
                .into(imageView)


            root.setOnClickListener {
                handler.onSelected(item)
            }
        }
    }

    fun updateData(list: List<Article>) {
        dataset.clear()
        dataset.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.articles_item, parent, false)
        return ViewHolder(rootView, handler)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}