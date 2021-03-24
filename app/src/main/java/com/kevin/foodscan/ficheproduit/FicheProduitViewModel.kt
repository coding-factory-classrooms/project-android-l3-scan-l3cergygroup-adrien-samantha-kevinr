package com.kevin.foodscan.ficheproduit

import androidx.lifecycle.ViewModel

data class FicheProduit(
    val id: Int,
    val nomProduit: String,
    val marqueProduit: String,
    val categoryProduit: String,
    val ingredientsProduit: String,
    val ingredientsInconnusProduit: Int,
    val imageProduit: String
    )

class FicheProduitViewModel : ViewModel() {
}