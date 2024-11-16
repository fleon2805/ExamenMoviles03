package com.leon.fabrizio.laboratoriocalificado03

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.leon.fabrizio.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class Ejercicio01 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        fetchProfesores()
    }

    private fun fetchProfesores() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getProfesores().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val profesores = response.body()?.teachers ?: emptyList()
                    if (profesores.isNotEmpty()) {
                        binding.recyclerView.adapter = ProfesorAdapter(this@Ejercicio01, profesores)
                    } else {
                        showToast(getString(R.string.no_profesores))
                    }
                } else {
                    showToast(getString(R.string.error_datos, response.code()))
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                showToast(getString(R.string.error_conexion, t.message))
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}



