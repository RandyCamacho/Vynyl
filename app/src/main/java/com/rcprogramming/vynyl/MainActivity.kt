package com.rcprogramming.vynyl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameProfile = findViewById<MaterialTextView>(R.id.username_profile)

        val username = intent.getStringExtra("Username")

        usernameProfile.text = username
    }
}