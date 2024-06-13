package org.d3if3104.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.d3if3104.myapplication.firebase.CartRepository
import org.d3if3104.myapplication.model.CartItem

class CartViewModel : ViewModel() {

    private val cartRepository = CartRepository()

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    init {
        fetchCartItems()
    }

    private fun fetchCartItems() {
        viewModelScope.launch {
            cartRepository.getCartItems(
                onCartItemsReceived = { items -> _cartItems.value = items },
                onFailure = { /* handle failure */ }
            )
        }
    }

    fun addItemToCart(cartItem: CartItem, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        cartRepository.addItemToCart(cartItem, {
            fetchCartItems()
            onSuccess()
        }, onFailure)
    }

    fun increaseQuantity(item: CartItem) {
        val updatedItem = item.copy(quantity = item.quantity + 1)
        updateItemInCart(updatedItem)
    }

    fun decreaseQuantity(item: CartItem) {
        if (item.quantity > 1) {
            val updatedItem = item.copy(quantity = item.quantity - 1)
            updateItemInCart(updatedItem)
        } else {
            removeItemFromCart(item)
        }
    }

    private fun updateItemInCart(item: CartItem) {
        viewModelScope.launch {
            cartRepository.updateQuantity(item)
            fetchCartItems()
        }
    }

    private fun removeItemFromCart(item: CartItem) {
        viewModelScope.launch {
            cartRepository.removeItemFromCart(item)
            fetchCartItems()
        }
    }
}
