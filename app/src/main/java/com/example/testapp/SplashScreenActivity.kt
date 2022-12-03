package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageView

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var image : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        image = findViewById(R.id.chaticon)
        image.alpha = 0f
        image.animate().setDuration(4000).alpha(1f).withEndAction(){
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
















    }
}