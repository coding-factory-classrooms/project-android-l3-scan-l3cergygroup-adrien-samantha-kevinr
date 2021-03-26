package com.kevin.foodscan.ficheproduit

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kevin.foodscan.R
import java.net.URL


class FicheProduitActivity : AppCompatActivity() {

    private val model: FicheProduitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fiche_produit)

        model.getProduit().observe(
                this,
                Observer { produit -> onProductUpdated(produit!!) }
        )

        model.loadProduit(3274080005003)
    }

    private fun onProductUpdated(produit: FicheProduit) {

        Log.i("FicheProduitActivity", "onProductUpdated: $produit")

        val fieldName: TextView = findViewById(R.id.nomProduitTextView)
        fieldName.text = produit.nomProduit

        val fieldBrand: TextView = findViewById(R.id.marqueProduitTextView)
        fieldBrand.text = produit.marqueProduit

        val fieldCategory: TextView = findViewById(R.id.categorieProduitTextView)
        fieldCategory.text = produit.categoryProduit

        val fieldIngredients: TextView = findViewById(R.id.ingredientsProduitTextView)
        fieldIngredients.text = produit.ingredientsProduit

        val fieldIngredientsunknown: TextView = findViewById(R.id.nbProduitsInconnusTextView)
        fieldIngredientsunknown.text = produit.ingredientsInconnusProduit.toString()
    }
}
