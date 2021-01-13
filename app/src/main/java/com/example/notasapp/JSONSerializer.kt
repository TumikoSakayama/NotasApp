package com.example.notasapp

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*
import java.lang.StringBuilder
import java.util.ArrayList

class JSONSerializer(private val nombrearchivo: String, private val context: Context) {
    @Throws(IOException::class, JSONException::class)
    fun guardar(nota: List<Nota>){
        val jArreglo = JSONArray()

        for(n in nota)
            jArreglo.put(n.convertirAJSON())

        var writer: Writer? = null
        try {
            val salida = context.openFileOutput(nombrearchivo, Context.MODE_PRIVATE)
            writer = OutputStreamWriter(salida)
            writer.write(jArreglo.toString())
        } finally {
            if(writer!=null){
                writer.close()
            }
        }
    }

    @Throws(IOException::class, JSONException::class)
    fun cargar(): ArrayList<Nota>{
        val listaNota = ArrayList<Nota>()
        var reader: BufferedReader? = null

        try {
            val `entrada` = context.openFileInput(nombrearchivo)
            reader = BufferedReader(InputStreamReader(`entrada`))
            val jsonString = StringBuilder()

            for (line in reader.readLine()){
                jsonString.append(line)
            }

            val jArreglo = JSONTokener(jsonString.toString()).nextValue() as JSONArray

            for(i in 0 until jArreglo.length()){
                listaNota.add(Nota(jArreglo.getJSONObject(i)))
            }
        } catch (e: FileNotFoundException) {

        } finally {
            reader!!.close()
        }
        return  listaNota
    }
}