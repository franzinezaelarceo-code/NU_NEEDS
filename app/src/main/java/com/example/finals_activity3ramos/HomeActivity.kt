package com.example.finals_activity3ramos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //val message = findViewById<TextView>(R.id.TV_Message)
        val messageView = intent.extras?.getString("message")
        val passwordView = intent.extras?.getString("password")
        val checkout = findViewById<Button>(R.id.BTN_CheckOut)

        //if (messageView.isNullOrBlank()){
      //      message.text = "No message received"
       // } else {
       //     message.text = "Welcome, $messageView \n Your password is $passwordView"
       // }

        checkout.setOnClickListener {
            val goBack = Intent(this, ViewCartActivity::class.java)
            startActivity(goBack)
        }
    }
}