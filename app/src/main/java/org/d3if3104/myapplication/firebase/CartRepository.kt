package org.d3if3104.myapplication.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.d3if3104.myapplication.model.CartItem

class CartRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addItemToCart(item: CartItem, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val user = auth.currentUser
        user?.let {
            db.collection("carts").document(user.uid)
                .collection("items").document(item.id)
                .set(item)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { e -> onFailure(e) }
        }
    }

    fun getCartItems(onFailure: (List<CartItem>) -> Unit, onCartItemsReceived: (List<CartItem>) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("carts").document(user.uid)
                .collection("items")
                .addSnapshotListener { snapshots, e ->
                    if (e != null) {
                        // Error fetching cart items
                        e.printStackTrace()
                        return@addSnapshotListener
                    }
                    if (snapshots != null) {
                        val cartItems = snapshots.toObjects(CartItem::class.java)
                        onCartItemsReceived(cartItems)
                    }
                }
        }
    }
}

private const val TAG = "DetailOrderScreen"

fun addToCart(item: CartItem, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
    val user = FirebaseAuth.getInstance().currentUser
    if (user != null) {
        val db = FirebaseFirestore.getInstance()
        db.collection("carts").document(user.uid)
            .collection("items").add(item)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Item added with ID: ${documentReference.id}")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding item", e)
                onFailure(e)
            }
    } else {
        Log.w(TAG, "User not logged in")
        onFailure(Exception("User not logged in"))
    }
}