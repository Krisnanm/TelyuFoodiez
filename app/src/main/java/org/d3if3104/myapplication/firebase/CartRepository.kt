package org.d3if3104.myapplication.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.d3if3104.myapplication.model.CartItem

class CartRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getCartItems(
        onCartItemsReceived: (List<CartItem>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val user = auth.currentUser
        user?.let {
            db.collection("carts").document(it.uid).collection("items")
                .get()
                .addOnSuccessListener { result ->
                    val items = result.map { doc ->
                        doc.toObject(CartItem::class.java).copy(id = doc.id)
                    }
                    onCartItemsReceived(items)
                }
                .addOnFailureListener { e -> onFailure(e) }
        }
    }

    fun updateQuantity(item: CartItem) {
        val user = auth.currentUser
        user?.let {
            val itemRef = db.collection("carts").document(it.uid).collection("items").document(item.id)
            itemRef.set(item)
        }
    }

    fun addItemToCart(cartItem: CartItem, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val user = auth.currentUser
        user?.let {
            val itemsCollection = db.collection("carts").document(it.uid).collection("items")
            val newItemRef = itemsCollection.document()
            newItemRef.set(cartItem.copy(id = newItemRef.id))
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { e -> onFailure(e) }
        }
    }

    fun removeItemFromCart(item: CartItem) {
        val user = auth.currentUser
        user?.let {
            val itemRef = db.collection("carts").document(it.uid).collection("items").document(item.id)
            itemRef.delete()
        }
    }
}
