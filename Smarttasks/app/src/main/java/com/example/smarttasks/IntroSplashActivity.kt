package com.example.smarttasks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smarttasks.ui.MainActivity

class IntroSplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        // Remove Title
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        //window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)
//        // Make Fullscreen
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        // Hide the toolbar
//        supportActionBar?.hide()
//
//        setContentView(R.layout.activity_introsplash)
//
//        // Using a handler to delay loading the MainActivity
//        Handler().postDelayed({
//            startActivity(Intent(this, MainActivity::class.java))
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
//            finish()
//        }, 2000)


        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
}