package com.example.mobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VacancyDetail : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vacancy_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val title = findViewById<TextView>(R.id.vacancyTitle)
        val description = findViewById<TextView>(R.id.vacancyDescription)
        val vacancyEmployer = findViewById<TextView>(R.id.vacancyEmployer)
        val salary = findViewById<TextView>(R.id.vacancySalary)
        val duration = findViewById<TextView>(R.id.vacancyDuration)


        val vacancyId = intent.getStringExtra("vacancyId")
        if (vacancyId != null) {
            val api = Api(this)
            api.getVacancyDetail(vacancyId.toInt()) { vacanciesObj->
                if (vacanciesObj != null) {
                    title.text = vacanciesObj.title
                    description.text = vacanciesObj.description

                    vacancyEmployer.text = vacanciesObj.employer
                    salary.text = "${vacanciesObj.salary} ₽"
                    duration.text = "${vacanciesObj.duration} мес."

                }
            }
        }

        val back = findViewById<TextView>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, AuthorizedActivity::class.java)
            this.startActivity(intent)
        }
    }
}