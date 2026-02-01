package com.example.finals_activity3ramos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FilingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filing)

        val btnPlus = findViewById<ImageView>(R.id.BTN_plus)
        val btnMinus = findViewById<ImageView>(R.id.BTN_minus)
        val txtQty = findViewById<TextView>(R.id.TV_quantity)
        val cartCount = findViewById<TextView>(R.id.TV_CartCount)
        val cartTotal = findViewById<TextView>(R.id.TV_CartTotal)
        val viewCart = findViewById<TextView>(R.id.TV_ViewCart)
        val stock = findViewById<TextView>(R.id.TV_StockStatus)
        val home = findViewById<ImageView>(R.id.BTN_Home)

        val productId = "ARCH_FILE_A4"
        val productName = "ARCH FILE FOLDER A4, 2 RINGS (3\")"
        val price = 85.0

        // INITIAL UI LOAD
        updateUI(txtQty, cartCount, productId)
        updateStockUI(productId, stock)
        updateCartTotal(cartTotal)

        btnPlus.setOnClickListener {

            val success = InventoryManager.decreaseStock(productId)

            if (success) {
                CartManager.addItem(productId, productName, price)
                updateUI(txtQty, cartCount, productId)
                updateStockUI(productId, stock)
                updateCartTotal(cartTotal)
                updateCartCount(cartCount)

            } else {
                Toast.makeText(this, "Out of stock", Toast.LENGTH_SHORT).show()
            }
        }

        btnMinus.setOnClickListener {

            val currentQty = CartManager.getItemQuantity(productId)

            if (currentQty > 0) {
                CartManager.removeItem(productId)
                InventoryManager.increaseStock(productId)
                updateUI(txtQty, cartCount, productId)
                updateStockUI(productId, stock)
                updateCartTotal(cartTotal)
                updateCartCount(cartCount)
            }
        }

        viewCart.setOnClickListener {
            startActivity(Intent(this, ViewCartActivity::class.java))
        }
        home.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun updateStockUI(productId: String,stock: TextView) {
        val product = InventoryManager.getProduct(productId)

        if (product != null && product.stock > 0) {
            stock.text = "In stock (${product.stock})"
            stock.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        } else {
            stock.text = "Out of stock"
            stock.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        }
    }

    private fun updateUI(
        txtQty: TextView,
        cartCount: TextView,
        productId: String
    ) {
        val item = CartManager.getItems().find { it.productId == productId }
        txtQty.text = item?.quantity?.toString() ?: "0"
        cartCount.text = CartManager.getTotalQuantity().toString()
    }
    private fun updateCartTotal(cartTotal: TextView) {
        cartTotal.text = "₱${CartManager.getTotalPrice()}"
    }

    private fun updateCartCount(cartCount: TextView) {
        cartCount.text = CartManager.getTotalQuantity().toString()
    }

}