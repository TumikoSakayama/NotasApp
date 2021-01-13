package com.example.notasapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdaptadorNota(private val mainActivity: MainActivity, private val listaNota: List<Nota>): RecyclerView.Adapter<AdaptadorNota.ListItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lista, parent, false)

        return  ListItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val nota = listaNota!![position]
        holder.Titulo.text = nota.titulo
        holder.Descripcion.text = nota.descripcion

        when{
            nota.idea -> holder.Estado.text = mainActivity.resources.getString(R.string.ideaTexto)
            nota.importante -> holder.Estado.text = mainActivity.resources.getString(R.string.importanteTexto)
            nota.porHacer -> holder.Estado.text = mainActivity.resources.getString(R.string.porHacerTexto)
        }
    }

    override fun getItemCount(): Int {
        if(listaNota != null){
            return  listaNota.size
        }
        return -1
    }

    inner class ListItemHolder(view: View) :
            RecyclerView.ViewHolder(view), View.OnClickListener {
        internal var Titulo =
                view.findViewById<View>(
                        R.id.textViewTitulo) as TextView

        internal var Descripcion =
                view.findViewById<View>(
                        R.id.textViewDescripcion) as TextView

        internal var Estado =
                view.findViewById<View>(
                        R.id.textViewEstado) as TextView

            init {
                view.isClickable = true
                view.setOnClickListener(this)
            }

            override fun onClick(view: View){
                mainActivity.mostraNota(adapterPosition)
            }

            }
}