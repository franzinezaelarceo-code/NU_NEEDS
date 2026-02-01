package com.example.finals_activity3ramos

object CartManager {

    private val cartItems = mutableMapOf<String, CartItem>()

    fun addItem(productId: String, name: String, price: Double) {

        val existing = cartItems[productId]

        if (existing != null) {
            existing.quantity++
        } else {
            cartItems[productId] = CartItem(
                productId = productId,
                name = name,
                price = price,
                quantity = 1
            )
        }
    }

    fun removeItem(productId: String) {
        val existing = cartItems[productId]

        if (existing != null) {
            existing.quantity--
            if (existing.quantity <= 0) {
                cartItems.remove(productId)
            }
        }
    }

    fun getItemQuantity(productId: String): Int {
        return cartItems[productId]?.quantity ?: 0
    }

    fun getTotalQuantity(): Int {
        return cartItems.values.sumOf { it.quantity }
    }

    fun getTotalPrice(): Double {
        return cartItems.values.sumOf { it.price * it.quantity }
    }

    fun getItems(): List<CartItem> {
        return cartItems.values.toList()
    }

    fun clearCart() {
        cartItems.clear()
    }
}
