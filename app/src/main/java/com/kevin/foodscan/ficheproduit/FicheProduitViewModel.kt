package com.kevin.foodscan.ficheproduit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

data class FicheProduit(
    val id: Int,
    val nomProduit: String,
    val marqueProduit: String,
    val categoryProduit: String,
    val ingredientsProduit: String,
    val ingredientsInconnusProduit: Int,
    val imageProduit: String,
    )

class FicheProduitViewModel : ViewModel() {

    private val produit = MutableLiveData<FicheProduit>()

    fun getProduit(): LiveData<FicheProduit> = produit

    fun loadProduit(){
        produit.value = FicheProduit(
            1,
            "BigMac",
            "McDonald",
            "Fast Food",
            "Pain Burger - Steak - Fromage - Salade",
            0,
            "")
    }
}