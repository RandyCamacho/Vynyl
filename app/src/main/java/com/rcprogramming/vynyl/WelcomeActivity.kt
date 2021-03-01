package com.rcprogramming.vynyl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.motion.widget.MotionLayout

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val signUp = findViewById<Button>(R.id.signup)
        val login = findViewById<Button>(R.id.login)

        signUp?.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, SignUpActivity::class.java))
            finish()
        }

        login?.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
            finish()
        }
    }
}