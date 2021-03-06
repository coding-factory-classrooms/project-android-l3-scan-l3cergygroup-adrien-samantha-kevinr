package com.kevin.foodscan.ficheproduit

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kevin.foodscan.R
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

data class FicheProduit(
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

    fun loadProduit(idProduit: Long){
        val request = Request.Builder()
                .url("https://world.openfoodfacts.org/api/v0/product/" + idProduit + ".json")
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //e.printStackTrace()
                Log.e("FicheProduitViewModel", "onFailure: " + e, )
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("ResultProduct: $name: $value")
                    }

                    val body = response.body!!.string()

                    try {
                        // some code
                        val jsonRoot = JSONObject(body)
                        val jsonProduct = jsonRoot.getJSONObject("product")
                        val name = jsonProduct.getString("generic_name_en")
                        val brand = jsonProduct.getString("brands")
                        val category = jsonProduct.getString("categories")
                        val ingredients = jsonProduct.getString("ingredients_text")
                        val unknownCount = jsonProduct.getInt("unknown_ingredients_n")
                        val image = jsonProduct.getString("image_ingredients_thumb_url")

                        produit.postValue(
                                FicheProduit(
                                        name,
                                        brand,
                                        category,
                                        ingredients,
                                        unknownCount,
                                        image
                                )
                        )

                    } catch (e: IOException) {
                        // handler
                        Log.e("FicheProduitViewModel", "onResponse: " + e)
                    }
                }
            }
        })
    }
}
