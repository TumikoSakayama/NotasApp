package com.example.notasapp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MostraNota : DialogFragment() {
    private var nota: Nota? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(this.activity!!)
        val inflater = activity!!.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialogo_mostrar, null)
        val txtTitulo = dialogView.findViewById(R.id.textViewTitulo) as TextView
        val txtDescripcion = dialogView.findViewById(R.id.textViewDescripcion) as TextView

        txtTitulo.text = nota!!.titulo
        txtDescripcion.text = nota!!.descripcion

        val txtImportante = dialogView.findViewById(R.id.textViewImportante) as TextView
        val txtPorHacer = dialogView.findViewById(R.id.textViewPorHacer) as TextView
        val txtIdea = dialogView.findViewById(R.id.textViewIdea) as TextView

        if (!nota!!.importante){
            txtImportante.visibility = View.GONE
        }

        if (!nota!!.porHacer){
            txtPorHacer.visibility = View.GONE
        }

        if (!nota!!.idea){
            txtIdea.visibility = View.GONE
        }

        val btnOK = dialogView.findViewById(R.id.btnOK) as Button

        builder.setView(dialogView).setMessage(resources.getString(R.string.your_note))

        btnOK.setOnClickListener{
            dismiss()
        }

        return builder.create()
    }

    fun enviarNotaSeleccionada(notaSeleccionada: Nota){
        nota = notaSeleccionada
    }
}