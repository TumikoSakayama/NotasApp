package com.example.notasapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    private var mostrarDivisiones : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val preferencia = getSharedPreferences("Notas App", Context.MODE_PRIVATE)
        mostrarDivisiones = preferencia.getBoolean("divisores", true)
        switch1.isChecked = mostrarDivisiones
        
        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            mostrarDivisiones = isChecked
        }
    }

    override fun onPause() {
        super.onPause()

        val preferencia = getSharedPreferences("Notas App", Context.MODE_PRIVATE)
        val editor = preferencia.edit()
        editor.putBoolean("divisores", mostrarDivisiones)
        editor.apply()
    }
}