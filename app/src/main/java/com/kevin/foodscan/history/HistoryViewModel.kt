package com.kevin.foodscan.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

data class Scan(
    val id: Int,
    val nomProduit: String,
    val code: String,
)

class HistoryViewModel : ViewModel() {

    private val scan = MutableLiveData<Scan>()

    fun getScan(): LiveData<Scan> = scan

    fun loadScan(){
        scan.value = Scan(
            1,
            "BigMac",
            "0332222222"
        )
    }
}