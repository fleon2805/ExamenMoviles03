package com.leon.fabrizio.laboratoriocalificado03

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leon.fabrizio.laboratoriocalificado03.databinding.ItemProfesorBinding

class ProfesorAdapter(
    private val context: Context,
    private val profesores: List<Profesor>
) : RecyclerView.Adapter<ProfesorAdapter.ProfesorViewHolder>() {

    inner class ProfesorViewHolder(private val binding: ItemProfesorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(profesor: Profesor) {
            binding.txtNombre.text = profesor.name
            binding.txtApellido.text = profesor.last_name
            Glide.with(context).load(profesor.imageUrl).into(binding.imgProfesor)

            // Configurar clics
            setupClickListeners(profesor)
        }

        private fun setupClickListeners(profesor: Profesor) {
            // Click simple: Llamar al número
            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${profesor.phone}"))
                context.startActivity(intent)
            }

            // Click largo: Enviar correo
            binding.root.setOnLongClickListener {
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${profesor.email}"))
                context.startActivity(intent)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesorViewHolder {
        val binding = ItemProfesorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfesorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfesorViewHolder, position: Int) {
        holder.bind(profesores[position])
    }

    override fun getItemCount(): Int = profesores.size
}


