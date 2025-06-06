package com.example.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignupEmployer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_employer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextLogin: EditText = findViewById(R.id.editTextLogin)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val editTextINN: EditText = findViewById(R.id.editTextINN)
        val editTextEmployerName: EditText = findViewById(R.id.editTextEmployerName)
        val buttonRegister: Button = findViewById(R.id.buttonRegister)
        val textViewAlreadyHaveAccount: TextView = findViewById(R.id.textViewAlreadyHaveAccount)

        buttonRegister.setOnClickListener {
            val login = editTextLogin.text.toString()
            val password = editTextPassword.text.toString()
            val editTextINN = editTextINN.text.toString()
            val editTextEmployerName = editTextEmployerName.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty() && editTextINN.isNotEmpty() && editTextEmployerName.isNotEmpty()) {
                // todo: signup
            } else {
                Toast.makeText(this, "Пожалуйста, заполните все необходимые поля", Toast.LENGTH_SHORT).show()
            }
        }

        textViewAlreadyHaveAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}