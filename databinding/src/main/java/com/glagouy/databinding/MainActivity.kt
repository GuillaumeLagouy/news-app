package com.glagouy.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.glagouy.databinding.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // version sans databinding : setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.textMain.text = "Changé grâce au data binding"
        binding.buttonHome.setOnClickListener {
            binding.textMain.text = "Click !"
        }

        // Comment ajouter le viewmodel à une activité

        GlobalScope.launch {
            delay(10000L) // Suspendre la coroutine 10 secondes
            println("World!") // Afficher un texte
        }
        println("Hello,")

    }
}
