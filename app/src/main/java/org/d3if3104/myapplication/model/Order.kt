package org.d3if3104.myapplication.model

data class Order(
    val id: String,
    val customerName: String,
    val address: String,
    val orderDetails: String,
    val price: String
)

val sampleOrders = listOf(
    Order("1", "Haikel","Gedung Asrama 5, no kamar 15", "2x Burger, 1x Fries", "Rp 32.000"),
    Order("2", "Fawwaz","Gedung Asrama 3, no kamar 5", "1x Pizza, 2x Soda", "Rp 24.000"),
    Order("3", "Rafi","Gedung Asrama 5, no kamar 12", "3x Tacos, 1x Burrito", "Rp 35.000")
)
