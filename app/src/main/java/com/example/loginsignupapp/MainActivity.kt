package com.example.loginsignupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.loginsignupapp.data.UserPreferences
import com.example.loginsignupapp.ui.auth.AuthActivity
import com.example.loginsignupapp.ui.home.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences= UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if(it==null) AuthActivity::class.java else HomeActivity::class.java
            startActivity(Intent(this, activity))
        })
        
    }
}