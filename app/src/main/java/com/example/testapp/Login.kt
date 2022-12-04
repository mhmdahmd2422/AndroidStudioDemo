package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtpwd : EditText
    private lateinit var loginbtn : Button
    private lateinit var signupbtn : Button
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail = findViewById(R.id.emailfield)
        edtpwd = findViewById(R.id.pwdfield)
        loginbtn = findViewById(R.id.loginbtn)
        signupbtn = findViewById(R.id.signupbtn)
        auth = FirebaseAuth.getInstance()

        signupbtn.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }


        loginbtn.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtpwd.text.toString()
            if (isValidEmail(email) && isValidPassword(password)) {
                login(email, password)
            } else {
                Toast.makeText(this@Login, "Invalid Username/Password Syntax!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //jump 3la home screen
                    //logging ll user
                    val intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "Wrong Username/Password Combination!", Toast.LENGTH_SHORT).show()

                }
            }
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.filter { it.isDigit() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) return false
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

        return true
    }
}