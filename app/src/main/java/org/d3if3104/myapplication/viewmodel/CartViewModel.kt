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
    val cartItems: StateFlow<List<CartItem>> get() = _cartItems

    init {
        fetchCartItems()
    }

    private fun fetchCartItems() {
        viewModelScope.launch {
            cartRepository.getCartItems(
                onCartItemsReceived = { items ->
                    _cartItems.value = items
                },
                onFailure = { e ->
                    // Handle error
                }
            )
        }
    }

    fun increaseQuantity(item: CartItem) {
        viewModelScope.launch {
            cartRepository.updateQuantity(item.copy(quantity = item.quantity + 1))
            fetchCartItems()
        }
    }

    fun decreaseQuantity(item: CartItem) {
        if (item.quantity > 1) {
            viewModelScope.launch {
                cartRepository.updateQuantity(item.copy(quantity = item.quantity - 1))
                fetchCartItems()
            }
        }
    }

    fun addItemToCart(
        cartItem: CartItem,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                cartRepository.addItemToCart(
                    cartItem = cartItem,
                    onSuccess = onSuccess,
                    onFailure = onFailure
                )
            } catch (e: Exception) {
                onFailure(e)
            }
            }
        }
}