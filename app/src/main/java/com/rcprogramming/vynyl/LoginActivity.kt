package com.rcprogramming.vynyl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.rcprogramming.vynyl.database.VynylDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var username: TextInputEditText
    lateinit var password: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signUp = findViewById<MaterialTextView>(R.id.signupView)
        val login = findViewById<Button>(R.id.login_Button)

        username = findViewById(R.id.login_username)
        password = findViewById(R.id.login_password)

        val db = VynylDatabase.getDatabase(this)

        var noUser = false;

        signUp?.setOnClickListener{
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            finish()
        }

        login?.setOnClickListener{
            if(usernameIsEmpty() && passwordIsEmpty()){

                    GlobalScope.launch {
                        var data = db.userDao().getUser(username.text.toString(), password.text.toString())

                        if (data == null) {
                            noUser = true
                        } else {
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("Username", username.text.toString())
                            startActivity(intent)
                            finish()
                        }
                    }

                if(noUser) {
                    username.error = "Username or Password Incorrect"
                    password.error = "Username or Password Incorrect"
                }
            }
        }

    }

    private fun usernameIsEmpty(): Boolean {
        val usernameInput = username.text.toString()

        if(usernameInput.isEmpty()){
            username.error = "Please Enter a Username"
            return false
        }
        return true
    }

    private fun passwordIsEmpty(): Boolean {
        val passwordInput = password.text.toString()
        if(passwordInput.isEmpty()){
            password.error = "Please Enter a Password"
            return false
        }
        return true
    }
}