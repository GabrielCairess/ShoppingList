package com.devmasterteam.shoppinglist

import android.graphics.Bitmap

data class Product(val name: String, val quantity: Int, val value: Double, val picture: Bitmap? = null)