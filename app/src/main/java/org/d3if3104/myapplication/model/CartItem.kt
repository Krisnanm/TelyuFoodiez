package org.d3if3104.myapplication.model

data class CartItem(
    val id: String = "",
    val name: String = "",
    val price: Int = 0,
    val quantity: Int = 0
) {
    // Konstruktor default tanpa argumen dibutuhkan oleh Firestore untuk deserialisasi
    constructor() : this("", "", 0, 0)
}
