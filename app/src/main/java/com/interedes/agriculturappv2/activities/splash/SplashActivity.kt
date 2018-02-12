package com.interedes.agriculturappv2.activities.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.interedes.agriculturappv2.activities.home.HomeActivity
import com.interedes.agriculturappv2.activities.home.HomeView
import com.interedes.agriculturappv2.activities.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, HomeActivity::class.java))
        finish()

    }

}
