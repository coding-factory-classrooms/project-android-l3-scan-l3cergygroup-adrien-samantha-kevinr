package com.kevin.foodscan.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.foodscan.R
import com.kevin.foodscan.ficheproduit.FicheProduit
import com.kevin.foodscan.history.HistoryViewModel
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import java.util.*

class HistoryActivity : AppCompatActivity() {
    private val model: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
    }
}
