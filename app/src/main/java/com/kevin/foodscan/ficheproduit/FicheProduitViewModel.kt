package com.kevin.foodscan.ficheproduit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.*
import okhttp3.Headers.*
import java.io.Closeable
import java.io.IOException
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
    private val client = OkHttpClient()


    fun getProduit(): LiveData<FicheProduit> = produit

    fun loadProduit(){

        CallAPI()

        produit.value = FicheProduit(
            1,
            "BigMac",
            "McDonald",
            "Fast Food",
            "Pain Burger - Steak - Fromage - Salade",
            0,
            ""
        )

    }

    private fun CallAPI(){
        val request = Request.Builder()
            .url("https://world.openfoodfacts.org/api/v0/product/737628064502.json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("ResultProduct: $name: $value")
                    }

                    println(response.body!!.string())

                    Log.i("FicheProduitViewModel", "onResponse: ${response.body.toString()}")
                }
            }
        })
    }
}