package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnsign_up: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        edtName = findViewById(R.id.namefield)
        edtEmail = findViewById(R.id.emailfield)
        edtPassword = findViewById(R.id.pwdfield)

        btnsign_up = findViewById(R.id.signupbtn)


        btnsign_up.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            signUp(name,email, password)
        }
    }


    private fun signUp(name:String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name, email, auth.currentUser?.uid!!)
                    val intent = Intent(this@SignUp, MainActivity::class.java)
                    finish()
                    startActivity(intent)


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignUp,"some error occured", Toast.LENGTH_SHORT).show()
                }
            }

    }
    private fun addUserToDatabase(name: String, email: String, uid: String) {
        dbref = FirebaseDatabase.getInstance().getReference()

        dbref.child("User").child(uid).setValue(User(name, email, uid))
    }
}
