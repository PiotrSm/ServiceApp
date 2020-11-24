package com.homeworkshop.serviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun startService(view: View) {
        startService(Intent(applicationContext,MyService::class.java))
    }
    fun stopService(view: View) {
        stopService(Intent(applicationContext,MyService::class.java))
    }
}