package com.example.temperatureconverter

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajustar los márgenes con WindowInsetsCompat
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los elementos del layout
        val etValor = findViewById<EditText>(R.id.et_valor)
        val btnCelsius = findViewById<Button>(R.id.btn_celsius)
        val btnFahrenheit = findViewById<Button>(R.id.btn_fahrenheit)
        val btnKelvin = findViewById<Button>(R.id.btn_kelvin)

        val tvCelsius = findViewById<TextView>(R.id.tv_celsius)
        val tvFahrenheit = findViewById<TextView>(R.id.tv_fahrenheit)
        val tvKelvin = findViewById<TextView>(R.id.tv_kelvin)

        // Función para convertir temperaturas
        fun convertirTemperatura(valor: Double, unidad: String) {
            val celsius: Double
            val fahrenheit: Double
            val kelvin: Double

            when (unidad) {
                "C" -> {
                    celsius = valor
                    fahrenheit = (valor * 9/5) + 32
                    kelvin = valor + 273.15
                }
                "F" -> {
                    celsius = (valor - 32) * 5/9
                    fahrenheit = valor
                    kelvin = celsius + 273.15
                }
                "K" -> {
                    celsius = valor - 273.15
                    fahrenheit = (celsius * 9/5) + 32
                    kelvin = valor
                }
                else -> return
            }

            tvCelsius.text = String.format("%.2f", celsius)
            tvFahrenheit.text = String.format("%.2f", fahrenheit)
            tvKelvin.text = String.format("%.2f", kelvin)
        }

        // Función para validar entrada y actualizar resultados
        fun manejarConversion(unidad: String) {
            val valorTexto = etValor.text.toString().trim()

            // Si el usuario no ingresa nada o ingresa texto inválido, mostrar "Error" en los resultados
            val valor = valorTexto.toDoubleOrNull()
            if (valor == null) {
                tvCelsius.text = "Error"
                tvFahrenheit.text = "Error"
                tvKelvin.text = "Error"
                return
            }

            // Si todo está bien, realizar la conversión
            convertirTemperatura(valor, unidad)
        }

        // Configurar botones para realizar la conversión
        btnCelsius.setOnClickListener { manejarConversion("C") }
        btnFahrenheit.setOnClickListener { manejarConversion("F") }
        btnKelvin.setOnClickListener { manejarConversion("K") }
    }
}
