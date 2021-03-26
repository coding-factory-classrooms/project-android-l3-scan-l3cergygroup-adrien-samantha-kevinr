package com.kevin.foodscan.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.foodscan.R
import com.kevin.foodscan.ficheproduit.FicheProduit
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.kevin.foodscan.history.HistoryViewModel
import java.util.*

class HistoryActivity : AppCompatActivity() {

    private val model: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)


        model.loadMovies()
        model.getScan().observe(
            this,
            Observer {scan -> onScanUpdated(scan)}
        )
        // on essaie de changer la valeur du textview de l'historique en mettant lattribut scan.code dedans mais la
        // valeur est nul car on arrive pas a appeler loadScan() sans que tout plante


        val numeroScan = model.getScansLiveData()

        val textView = findViewById<TextView>(R.id.scantextView)
        textView.refreshDrawableState()
        textView.text = model.getScantrue()?.get(0)?.code
        model.loadScan()
        //textView.text = model.getScan().value?.code

    }
    private fun onScanUpdated(scan: Scan) {
        Log.i("HistoryActivity", "onScanUpdated: $scan")
    }
}