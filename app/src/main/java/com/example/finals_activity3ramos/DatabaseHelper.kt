package com.example.finals_activity3ramos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 3

        // Users Table
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRST_NAME = "first_name"
        private const val COLUMN_LAST_NAME = "last_name"
        private const val COLUMN_MIDDLE_NAME = "middle_name"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

        // Categories Table
        private const val TABLE_CATEGORIES = "categories"
        private const val COLUMN_CATEGORY_ID = "category_id"
        private const val COLUMN_CATEGORY_NAME = "category_name"

        // Products Table
        private const val TABLE_PRODUCTS = "products"
        private const val COLUMN_PRODUCT_ID = "product_id"
        private const val COLUMN_PRODUCT_CATEGORY_ID = "category_id"
        private const val COLUMN_PRODUCT_NAME = "name"
        private const val COLUMN_PRODUCT_DESCRIPTION = "description"
        private const val COLUMN_PRODUCT_PRICE = "price"
        private const val COLUMN_PRODUCT_STOCK = "stock"
        private const val COLUMN_PRODUCT_IMAGE_URL = "image_url"

        // Cart Table
        private const val TABLE_CART = "cart"
        private const val COLUMN_CART_ID = "cart_id"
        private const val COLUMN_CART_USER_ID = "user_id"
        private const val COLUMN_CART_PRODUCT_ID = "product_id"
        private const val COLUMN_CART_QUANTITY = "quantity"

        // Orders Table
        private const val TABLE_ORDERS = "orders"
        private const val COLUMN_ORDER_ID = "order_id"
        private const val COLUMN_ORDER_USER_ID = "user_id"
        private const val COLUMN_ORDER_DATE = "date"
        private const val COLUMN_ORDER_TOTAL_AMOUNT = "total_amount"

        // Order Items Table
        private const val TABLE_ORDER_ITEMS = "order_items"
        private const val COLUMN_ORDER_ITEM_ID = "order_item_id"
        private const val COLUMN_ORDER_ITEM_ORDER_ID = "order_id"
        private const val COLUMN_ORDER_ITEM_PRODUCT_ID = "product_id"
        private const val COLUMN_ORDER_ITEM_QUANTITY = "quantity"
        private const val COLUMN_ORDER_ITEM_PRICE = "price"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create Users Table
        val createUsersTable = ("CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FIRST_NAME + " TEXT,"
                + COLUMN_LAST_NAME + " TEXT,"
                + COLUMN_MIDDLE_NAME + " TEXT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT" + ")")
        db?.execSQL(createUsersTable)

        // Create Categories Table
        val createCategoriesTable = ("CREATE TABLE " + TABLE_CATEGORIES + "("
                + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CATEGORY_NAME + " TEXT" + ")")
        db?.execSQL(createCategoriesTable)

        // Create Products Table
        val createProductsTable = ("CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_PRODUCT_ID + " TEXT PRIMARY KEY,"
                + COLUMN_PRODUCT_CATEGORY_ID + " INTEGER,"
                + COLUMN_PRODUCT_NAME + " TEXT,"
                + COLUMN_PRODUCT_DESCRIPTION + " TEXT,"
                + COLUMN_PRODUCT_PRICE + " REAL,"
                + COLUMN_PRODUCT_STOCK + " INTEGER,"
                + COLUMN_PRODUCT_IMAGE_URL + " TEXT" + ")")
        db?.execSQL(createProductsTable)

        // Create Cart Table
        val createCartTable = ("CREATE TABLE " + TABLE_CART + "("
                + COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CART_USER_ID + " INTEGER,"
                + COLUMN_CART_PRODUCT_ID + " TEXT,"
                + COLUMN_CART_QUANTITY + " INTEGER" + ")")
        db?.execSQL(createCartTable)

        // Create Orders Table
        val createOrdersTable = ("CREATE TABLE " + TABLE_ORDERS + "("
                + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ORDER_USER_ID + " INTEGER,"
                + COLUMN_ORDER_DATE + " TEXT,"
                + COLUMN_ORDER_TOTAL_AMOUNT + " REAL" + ")")
        db?.execSQL(createOrdersTable)

        // Create Order Items Table
        val createOrderItemsTable = ("CREATE TABLE " + TABLE_ORDER_ITEMS + "("
                + COLUMN_ORDER_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ORDER_ITEM_ORDER_ID + " INTEGER,"
                + COLUMN_ORDER_ITEM_PRODUCT_ID + " TEXT,"
                + COLUMN_ORDER_ITEM_QUANTITY + " INTEGER,"
                + COLUMN_ORDER_ITEM_PRICE + " REAL" + ")")
        db?.execSQL(createOrderItemsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CATEGORIES")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_CART")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ORDERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ORDER_ITEMS")
        onCreate(db)
    }

    fun addUser(firstName: String, lastName: String, middleName: String, username: String, pass: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_FIRST_NAME, firstName)
        contentValues.put(COLUMN_LAST_NAME, lastName)
        contentValues.put(COLUMN_MIDDLE_NAME, middleName)
        contentValues.put(COLUMN_USERNAME, username)
        contentValues.put(COLUMN_PASSWORD, pass)
        val result = db.insert(TABLE_USERS, null, contentValues)
        return result != -1L
    }

    fun checkUser(username: String, pass: String): Boolean {
        val db = this.readableDatabase
        val columns = arrayOf(COLUMN_ID)
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, pass)
        val cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null)
        val count = cursor.count
        cursor.close()
        return count > 0
    }
}
