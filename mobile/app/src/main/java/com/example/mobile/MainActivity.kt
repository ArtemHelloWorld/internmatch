package com.example.mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    var myContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        myContext = getApplicationContext();

        val sharedPreferences = SharedPreferences()
        var accessToken = sharedPreferences.get("access_token", context = this)

        if (accessToken != null && accessToken != "") {
            val intent = Intent(this, AuthorizedActivity::class.java)
            this.startActivity(intent)
        }
        else {
            // todo : refresh
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
        }
    }

}