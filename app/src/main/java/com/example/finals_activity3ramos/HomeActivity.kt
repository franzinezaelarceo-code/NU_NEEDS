package com.example.finals_activity3ramos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
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
            toggleSidePanel(sidePanel)
        }

        findViewById<View>(R.id.main).setOnClickListener {
            if (isPanelOpen) {
                toggleSidePanel(sidePanel)
            }
        }

        sidePanel.setOnClickListener {
        }


        checkoutButton.setOnClickListener {
            val intent = Intent(this, ViewCartActivity::class.java)
            startActivity(intent)
        }

        // Set up category click listeners (DOES NOTHING YET)
        setupCategoryClicks(sidePanel)
    }

    private fun toggleSidePanel(sidePanel: View) {
        if (isPanelOpen) {
            // CLOSE the panel
            sidePanel.animate()
                .translationX(-250f)
                .setDuration(300)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .withEndAction {
                    sidePanel.visibility = View.GONE
                }
                .start()
        } else {
            // OPEN the panel
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

    private fun setupCategoryClicks(sidePanel: View) {
        // Category click listeners - DO NOTHING YET
        val categoryIds = listOf(
            R.id.category_filing,
            R.id.category_fasteners,
            R.id.category_cutting,
            R.id.category_writing,
            R.id.category_ink,
            R.id.category_paper,
            R.id.category_tapes
        )

        categoryIds.forEach { id ->
            findViewById<View>(id).setOnClickListener {
                // Just show a toast (you'll replace this later)
                when (id) {
                    R.id.category_filing -> Toast.makeText(this, "Filing clicked", Toast.LENGTH_SHORT).show()
                    R.id.category_fasteners -> Toast.makeText(this, "Fasteners clicked", Toast.LENGTH_SHORT).show()
                    R.id.category_cutting -> Toast.makeText(this, "Cutting clicked", Toast.LENGTH_SHORT).show()
                    R.id.category_writing -> Toast.makeText(this, "Writing clicked", Toast.LENGTH_SHORT).show()
                    R.id.category_ink -> Toast.makeText(this, "Ink clicked", Toast.LENGTH_SHORT).show()
                    R.id.category_paper -> Toast.makeText(this, "Paper clicked", Toast.LENGTH_SHORT).show()
                    R.id.category_tapes -> Toast.makeText(this, "Tapes clicked", Toast.LENGTH_SHORT).show()
                }

                // Close the panel
                toggleSidePanel(sidePanel)

                // TODO: Later you'll add code here to show category content
            }
        }
    }

    override fun onBackPressed() {
        val sidePanel = findViewById<View>(R.id.side_panel)
        if (isPanelOpen) {
            toggleSidePanel(sidePanel)
        } else {
            super.onBackPressed()
        }
    }
}