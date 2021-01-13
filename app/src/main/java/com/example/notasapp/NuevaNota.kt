package com.example.notasapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class NuevaNota: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        val inflater = activity!!.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialogo_nuevo, null)
        val editarTitulo = dialogView.findViewById(R.id.editTitulo) as EditText
        val editarDescripcion = dialogView.findViewById(R.id.editDescripcion) as EditText
        val checkBoxIdea = dialogView.findViewById(R.id.checkBoxIdea) as CheckBox
        val checBoxPorHacer = dialogView.findViewById(R.id.checkBoxPorHacer) as CheckBox
        val checkBoxImportante =  dialogView.findViewById(R.id.checkBoxImportante) as CheckBox
        val btnCancelar =  dialogView.findViewById(R.id.btnCancelar) as Button
        val btnOK = dialogView.findViewById(R.id.btnOK) as Button

        builder.setView(dialogView).setMessage(resources.getString(R.string.add_new_note))

        btnCancelar.setOnClickListener {
            dismiss()
        }

        btnOK.setOnClickListener {
            val nuevaNota = Nota()
            nuevaNota.titulo = editarTitulo.text.toString()
            nuevaNota.descripcion = editarDescripcion.text.toString()
            nuevaNota.idea = checkBoxIdea.isChecked
            nuevaNota.porHacer = checBoxPorHacer.isChecked
            nuevaNota.importante = checkBoxImportante.isChecked

            val callingActivity = activity as MainActivity?
            callingActivity!!.crearNuevaNota(nuevaNota)

            dismiss()
        }

        return  builder.create()

    }
}