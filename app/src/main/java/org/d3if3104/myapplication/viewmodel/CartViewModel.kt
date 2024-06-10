package org.d3if3104.myapplication.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import org.d3if3104.myapplication.firebase.CartRepository
import org.d3if3104.myapplication.model.CartItem

class CartViewModel : ViewModel() {
    private val repository = CartRepository()
    val cartItems = mutableStateListOf<CartItem>()

    fun addItemToCart(item: CartItem) {
        viewModelScope.launch {
            repository.addItemToCart(item,
                onSuccess = {
                    // Handle success
                },
                onFailure = { e ->
                    // Handle error
                    e.printStackTrace()
                }
            )
        }
    }

    fun getCartItems() {
        repository.getCartItems(
            onCartItemsReceived = { items ->
                cartItems.clear()
                cartItems.addAll(items)
            },
            onFailure = { e ->
                // Handle error
                e.printStackTrace()
            }
        )
    }
}

private fun <E> List<E>.printStackTrace() {
    TODO("Not yet implemented")
}