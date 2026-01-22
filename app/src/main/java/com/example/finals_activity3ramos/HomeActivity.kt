package com.example.finals_activity3ramos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    private var isPanelOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val burgerButton = findViewById<ImageView>(R.id.burger_button)
        val sidePanel = findViewById<View>(R.id.side_panel)
        val checkoutButton = findViewById<Button>(R.id.checkout_button)

        burgerButton.setOnClickListener {
            if (isPanelOpen) {
                sidePanel.animate()
                    .translationX(-250f)
                    .setDuration(300)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .withEndAction {
                        sidePanel.visibility = View.GONE
                    }
                    .start()
            } else {
                sidePanel.visibility = View.VISIBLE
                sidePanel.translationX = -250f
                sidePanel.animate()
                    .translationX(0f)
                    .setDuration(300)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .start()
            }

            isPanelOpen = !isPanelOpen
        }

        findViewById<View>(R.id.main).setOnClickListener {
            if (isPanelOpen) {
                burgerButton.performClick()
            }
        }

        sidePanel.setOnClickListener {

        }

        checkoutButton.setOnClickListener {
            val intent = Intent(this, ViewCartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (isPanelOpen) {
            findViewById<ImageView>(R.id.burger_button).performClick()
        } else {
            super.onBackPressed()
        }
    }
}