package com.example.lab1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        inputForm.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    outputLabel.text = getString(R.string.morda)
                }
                else if (s.length == 1) {
                    val ch = s.first()
                    outputLabel.text = when {
                        ch.isDigit() -> "Это цифра!"
                        charArrayOf('&', '#', '<').contains(ch) -> "Это спецсимвол!"
                        else -> "Непредусмотренный вариант"
                    }
                }
                else {
                    s.replace(0, s.length, s.last().toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}