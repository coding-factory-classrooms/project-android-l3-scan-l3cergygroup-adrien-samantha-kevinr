package com.kevin.foodscan.history
import okhttp3.*
import okhttp3.Headers.*
import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import android.util.Log
import org.json.JSONObject
import java.io.Closeable
import java.io.IOException

data class Scan(
    val code: String,
    val unknownIngredients: Int
)

class HistoryViewModel : ViewModel() {

    private val scan = MutableLiveData<Scan>()
    private val client = OkHttpClient()

    fun getScan(): LiveData<Scan> = scan

    fun loadScan(){
        val request = Request.Builder()
            .url("https://world.openfoodfacts.org/api/v0/product/737628064502.json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    for ((name, value) in response.headers) {
                        println("ResultProduct: $name: $value")
                    }
                    val body = response.body!!.string()
                    val jsonRoot = JSONObject(body)
                    val JSONProduct = jsonRoot.getJSONObject("product")
                    val unknownCount = JSONProduct.getInt("unknown_ingredients_n");
                    Log.i("TestProduct", "onResponse: $unknownCount")
                    scan.value = Scan(
                        "0133333",
                        unknownCount
                    )
                }
            }

        })
    }
}