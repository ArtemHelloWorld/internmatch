package com.example.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class AuthorizedActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorized)
        loadFragment(VacanciesList())
        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)!!
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.vacanciesList -> {
                    loadFragment(VacanciesList())
                    true
                }
                R.id.monitoring -> {
                    loadFragment(Monitoring())
                    true
                }
                R.id.profile -> {
                    loadFragment(Profile())
                    true
                }

                else -> {false}
            }
        }
    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()
    }

}
