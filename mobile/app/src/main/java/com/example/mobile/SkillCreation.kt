package com.example.mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SkillCreation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_skill_creation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextSkillTitle: EditText = findViewById(R.id.editTextSkillTitle)
        val editTextSkillRate: EditText = findViewById(R.id.editTextSkillRate)

        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonCancel: Button = findViewById(R.id.buttonCancel)

        buttonAdd.setOnClickListener {
            val jobTitle = editTextSkillTitle.text.toString()
            val description = editTextSkillRate.text.toString()

            if (jobTitle.isNotEmpty() && description.isNotEmpty()) {
                // todo: создание вакансии
            } else {
                Toast.makeText(this, "Пожалуйста заполните все необходимые поля", Toast.LENGTH_SHORT).show()
            }
        }

        buttonCancel.setOnClickListener {
            finish()
        }
    }
}