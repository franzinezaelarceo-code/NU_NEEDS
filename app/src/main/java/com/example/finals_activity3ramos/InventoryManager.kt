import com.example.finals_activity3ramos.Product

object InventoryManager {

    private val products = mutableMapOf<String, Product>()

    init {
        addProduct(
            Product(
                id = "ARCH_FILE_A4",
                name = "ARCH FILE FOLDER",
                stock = 5,
                initialStock = 5,
                price = 85.0
            )
        )
    }

        fun addProduct(product: Product) {
        products[product.id] = product
    }

    fun getProduct(productId: String): Product? {
        return products[productId]
    }

    fun decreaseStock(productId: String): Boolean {
        val product = products[productId] ?: return false

        if (product.stock > 0) {
            product.stock--
            return true
        }
        return false
    }

    fun increaseStock(productId: String) {
        val product = products[productId] ?: return
        if (product.stock < product.initialStock) {
            product.stock++
            products[productId]?.stock?.plus(1) ?: 0
        }
    }

}
