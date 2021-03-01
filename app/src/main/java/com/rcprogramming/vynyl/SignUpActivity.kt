package com.rcprogramming.vynyl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.rcprogramming.vynyl.database.VynylDatabase
import com.rcprogramming.vynyl.entity.UserEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    lateinit var username: TextInputEditText
    lateinit var name: TextInputEditText
    lateinit var password: TextInputEditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        val login = findViewById<MaterialTextView>(R.id.loginView)

        val register = findViewById<Button>(R.id.register)

        val db = VynylDatabase.getDatabase(this)

        username = findViewById(R.id.username)
        name = findViewById(R.id.name)
        password = findViewById(R.id.password)

        login?.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            finish()
        }

        register?.setOnClickListener {
          if(validateUsername() && validateName() && validatePassword()) {

              GlobalScope.launch {
                  db.userDao().insertUser(UserEntity(0, username.text.toString(), name.text.toString(), password.text.toString()))
                  //db.userDao().nukeTable()
              }

              val intent = Intent(this@SignUpActivity, MainActivity::class.java)
              intent.putExtra("Username", username.text.toString())
              startActivity(intent)
              finish()
          }
        }

    }

    private fun validateUsername(): Boolean {
        val usernameREGEX = Pattern.compile("^" + ".{4,}" + "$");

        val usernameInput = username.text.toString()

        if(usernameInput.isEmpty()){
            username.error = "Please Enter a Username"
            return false
       } else if(!usernameREGEX.matcher(usernameInput).matches()){
            username.error = "Username must have at least 4 characters long"
            return false
        }

        return true
    }

    private fun validateName(): Boolean {
        val nameInput = name.text.toString()

        if(nameInput.isEmpty()){
            name.error = "Please Enter a Name"
            return false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        val specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_"
        val passwordREGEX = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[$specialCharacters])" +  //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$");

        val passwordInput = password.text.toString()

        if(passwordInput.isEmpty()) {
            password.error = "Please Enter a Password"

            return false
        } else if(!passwordREGEX.matcher(passwordInput).matches()){
            password.error = "Password must have 1 letter, 1 number, 1 special character and " +
                    "be at least 8 characters long"
            return false
        }
        return true
    }

}