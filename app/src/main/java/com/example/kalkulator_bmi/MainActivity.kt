package com.example.kalkulator_bmi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kalkulator_bmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var bmiValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCount.setOnClickListener {
            calculateBMI()
        }

        binding.btnToast.setOnClickListener {
            displayBMIStatus()
        }
    }

    private fun calculateBMI() {
        val beratBadanStr = binding.editTextBb.text.toString()
        val tinggiBadanStr = binding.editTextTb.text.toString()

        if (beratBadanStr.isNotEmpty() && tinggiBadanStr.isNotEmpty()) {
            val beratBadan = beratBadanStr.toDouble()
            val tinggiBadan = tinggiBadanStr.toDouble() / 100.0

            bmiValue = beratBadan / (tinggiBadan * tinggiBadan)

            binding.textNumber.text = String.format("%.2f", bmiValue)
        } else {
            showToast("Masukkan berat dan tinggi badan terlebih dahulu.")
        }
    }

    private fun displayBMIStatus() {
        if (bmiValue != 0.0) {
            val bmiStatus = getBMIStatus(bmiValue)
            showToast("BMI: %.2f (%s)".format(bmiValue, bmiStatus))
        } else {
            showToast("Hitung BMI terlebih dahulu.")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun getBMIStatus(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Indeks berat badan anda kurang"
            bmi < 24.9 -> "Indeks berat badan anda normal"
            bmi < 29.9 -> "Indeks berat badan berlebih"
            else -> "Anda mengalami Obesitas"
        }
    }
}
