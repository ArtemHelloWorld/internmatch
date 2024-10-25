package com.example.mobile

import android.R.attr.duration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class VacanciesList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vacancies_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val vacanciesList: RecyclerView = findViewById(R.id.vacanciesList)
        val vacancies = ArrayList<Vacancy>()

        val api = Api()
        api.getVacanciesList() { vacanciesObjs ->
            for (vacanciesObj in vacanciesObjs){
                vacancies.add(vacanciesObj)
            }
        }

        vacanciesList.layoutManager = LinearLayoutManager(this)
        vacanciesList.adapter = VacanciesAdapter(vacancies, this)
    }
}