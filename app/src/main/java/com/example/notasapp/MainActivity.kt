package com.example.notasapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var mostrarDivisiones: Boolean = false
    //private val listaNota = ArrayList<Nota>()
    private var mJSONSerializer : JSONSerializer? = null
    private var listaNota: ArrayList<Nota>? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: AdaptadorNota? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener { view ->
            val dialogo = NuevaNota()
            dialogo.show(supportFragmentManager, "")
        }

        mJSONSerializer = JSONSerializer("NotasApp.json", applicationContext)

        try {
            listaNota = mJSONSerializer!!.cargar()
        } catch (e: Exception) {
            listaNota = ArrayList()
            Log.e("Error cargando notas: ","", e)
        }

        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        adapter = AdaptadorNota(this, this.listaNota!!)
        val layoutManager = LinearLayoutManager(applicationContext)

        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = adapter


    }

    fun crearNuevaNota(n: Nota) {
        listaNota!!.add(n)
        adapter!!.notifyDataSetChanged()
    }

    fun mostraNota(notaPorMostrar: Int){
        val dialogo = MostraNota()
        dialogo.enviarNotaSeleccionada(listaNota!![notaPorMostrar])
        dialogo.show(supportFragmentManager, "")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()
        guardarNotas()
    }
    override fun onResume() {
        super.onResume()

        val preferencia = getSharedPreferences("Notas App", Context.MODE_PRIVATE)
        mostrarDivisiones = preferencia.getBoolean("divisores", true)

        if (mostrarDivisiones)
            recyclerView!!.addItemDecoration(
                DividerItemDecoration(
                    this, LinearLayoutManager.VERTICAL))
        else {
            // check there are some dividers
            // or the app will crash
            if (recyclerView!!.itemDecorationCount > 0)
                recyclerView!!.removeItemDecorationAt(0)
        }
    }

    private fun guardarNotas(){
        try {
            mJSONSerializer!!.guardar(this.listaNota!!)
        } catch (e: Exception) {
            Log.e("Error guardando notas: ", "", e)
        }
    }
}