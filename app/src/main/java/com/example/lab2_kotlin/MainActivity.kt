package com.example.lab2_kotlin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab2_kotlin.R
import com.example.lab2_kotlin.util.calculate
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputForm: TextInputEditText = findViewById(R.id.main_form)
        val outputLabel: TextView = findViewById(R.id.main_label)

        inputForm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    outputLabel.text = getString(R.string.face)
                } else {
                    try {
                        val epsilon = s.toString().toFloatOrNull()
                        if (epsilon != null) {
                            if (epsilon <= 0) {
                                outputLabel.text = getString(R.string.epsilon_error2_text)
                            } else if (epsilon > 1) {
                                outputLabel.text = getString(R.string.epsilon_error1_text)
                            } else {
                                // Здесь epsilon находится в диапазоне (0, 1]
                                val (sum, lastTerm, iterations) = calculate(epsilon)
                                outputLabel.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                                outputLabel.text = getString(
                                    R.string.result,
                                    getString(R.string.sum), sum,
                                    getString(R.string.last_term), lastTerm,
                                    getString(R.string.iterations), iterations
                                )
                            }
                        }
                    } catch (ex: NumberFormatException) {
                        outputLabel.text = getString(R.string.not_number_ex_text)
                    } catch (ex: Exception) {
                        outputLabel.text = ex.message ?: "Произошла ошибка"
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
