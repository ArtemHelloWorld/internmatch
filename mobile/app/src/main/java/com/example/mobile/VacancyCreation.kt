package com.example.mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VacancyCreation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vacancy_creation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextJobTitle: EditText = findViewById(R.id.editTextJobTitle)
        val editTextDescription: EditText = findViewById(R.id.editTextDescription)
        val editTextSkills: EditText = findViewById(R.id.editTextSkills)
        val editTextSalary: EditText = findViewById(R.id.editTextSalary)
        val editTextDuration: EditText = findViewById(R.id.editTextDuration)

        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonCancel: Button = findViewById(R.id.buttonCancel)

        buttonAdd.setOnClickListener {
            val jobTitle = editTextJobTitle.text.toString()
            val description = editTextDescription.text.toString()
            val skills = editTextSkills.text.toString()
            val salary = editTextSalary.text.toString()
            val duration = editTextDuration.text.toString()

            if (jobTitle.isNotEmpty() && description.isNotEmpty() &&
                skills.isNotEmpty() && salary.isNotEmpty() && duration.isNotEmpty()) {
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