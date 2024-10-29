package com.example.mobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EchoVacancyDetail : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_echo_vacancy_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val vacancyEmployer = findViewById<TextView>(R.id.vacancyEmployer)
        val vacancyTitle = findViewById<TextView>(R.id.vacancyTitle)
        val vacancySkills = findViewById<TextView>(R.id.vacancySkills)
        val vacancyIntern = findViewById<TextView>(R.id.vacancyIntern)
        val vacancyTimeCreated = findViewById<TextView>(R.id.vacancyTimeCreated)
        val internSkills = findViewById<ListView>(R.id.internSkills)


        val echoVacancyId = intent.getIntExtra("echoVacancyId", -1)
        val internId = intent.getIntExtra("internId", -1)
        val api = Api(this)

        if (echoVacancyId != -1) {
            api.getEchoVacancyDetail(echoVacancyId) { echoVacanciesObj ->
                if (echoVacanciesObj != null) {
                    this.runOnUiThread(Runnable {
                        vacancyEmployer.text =
                            "Стажировка в ${echoVacanciesObj.vacancy_meta.employer}"
                        vacancyTitle.text = echoVacanciesObj.vacancy_meta.title
                        vacancySkills.text = echoVacanciesObj.vacancy_meta.skills
                        vacancyIntern.text = echoVacanciesObj.intern_username
                        vacancyTimeCreated.text = echoVacanciesObj.time_created.slice(0..18).replace('T', ' ')
                    })
                }
            }
        }

        if (internId != -1) {
            api.getInternInfo(internId as Int) { internInfoObj ->
                if (internInfoObj != null){
                    this.runOnUiThread(Runnable {
                        val itemDataList = ArrayList<Map<String, Any>>()
                        for (skill in internInfoObj.skills) {
                            val listItemMap: MutableMap<String, Any> = HashMap()
                            listItemMap["name"] = skill.name
                            listItemMap["rate"] = "${skill.rate}/10"
                            itemDataList.add(listItemMap)
                        }
                        val simpleAdapter = SimpleAdapter(
                            this,
                            itemDataList,
                            android.R.layout.simple_list_item_2,
                            arrayOf("name", "rate"),
                            intArrayOf(android.R.id.text1, android.R.id.text2)
                        )
                        internSkills.adapter = simpleAdapter
                    })
                }
            }
        }

        val back = findViewById<TextView>(R.id.back)
        back.setOnClickListener {
            finish()
        }
    }
}