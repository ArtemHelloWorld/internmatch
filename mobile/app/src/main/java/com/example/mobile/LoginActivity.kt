package com.example.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val loginBtn = findViewById<Button>(R.id.login)
        val errorMessage = findViewById<TextView>(R.id.errorMessage)

        loginBtn.setOnClickListener {
            val api = Api()
            api.login(username.text.toString(), password.text.toString()) { jwtToken ->
                if (jwtToken != null) {
                    val sharedPreferences = SharedPreferences()
                    sharedPreferences.save("access_token", jwtToken.access, context = this)
                    sharedPreferences.save("refresh_token", jwtToken.refresh, context = this)
                    
                    val intent = Intent(this, MainActivity::class.java)
                    this.startActivity(intent)
                }
                else{
                    errorMessage.isVisible = true
                }
            }

        }


    }
}