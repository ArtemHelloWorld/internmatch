package com.example.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignupChoose : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_choose)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val internButton = findViewById<Button>(R.id.button_intern)
        val employerButton = findViewById<Button>(R.id.button_employer)

        internButton.setOnClickListener {
            val intent = Intent(this, SignupIntern::class.java)
            startActivity(intent)
        }

        employerButton.setOnClickListener {
            val intent = Intent(this, SignupEmployer::class.java)
            startActivity(intent)
        }
    }
}