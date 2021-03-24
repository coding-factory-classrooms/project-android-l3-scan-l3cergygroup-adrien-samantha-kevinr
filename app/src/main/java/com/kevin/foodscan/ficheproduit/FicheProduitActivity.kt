package com.kevin.foodscan.ficheproduit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.kevin.foodscan.R
import androidx.lifecycle.Observer
import java.util.*

class FicheProduitActivity : AppCompatActivity() {

    private val model: FicheProduitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fiche_produit)

        model.getProduit().observe(
            this,
            Observer {produit -> onProductUpdated(produit)}
        )
    }

    private fun onProductUpdated(produit: FicheProduit) {
        TODO("Not yet implemented")
    }
}
