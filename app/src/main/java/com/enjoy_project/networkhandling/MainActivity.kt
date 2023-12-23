package com.enjoy_project.networkhandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private val retrofit = RetrofitImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testButton = findViewById<Button>(R.id.testButton)
        val testText = findViewById<TextView>(R.id.testText)

        testButton.setOnClickListener {

            retrofit.getUserList {

                Log.d("test", it.toString())

                testText.text = it.toString()

            }


        }

    }
}