package com.glagouy.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.glagouy.news.fragments.ArticlesFragment
import com.glagouy.news.fragments.FavoriteFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val articlesFragment = ArticlesFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.articlesFragmentContainer, articlesFragment)
            addToBackStack(null)
        }.commit()


        accueilNav.setOnClickListener {
            //Faire un back

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.articlesFragmentContainer, ArticlesFragment())
                addToBackStack(null)
            }.commit()
        }

        favNav.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.articlesFragmentContainer, FavoriteFragment())
                addToBackStack(null)
            }.commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1){
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
