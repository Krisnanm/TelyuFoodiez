package org.d3if3104.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.d3if3104.myapplication.model.User

class UserViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _isRegistrationSuccessful = MutableStateFlow(false)
    val isRegistrationSuccessful: StateFlow<Boolean> = _isRegistrationSuccessful

    private val _registrationErrorMessage = MutableStateFlow<String?>(null)
    val registrationErrorMessage: StateFlow<String?> = _registrationErrorMessage

    fun registerUser(user: User, password: String) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(user.email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = auth.currentUser
                        user.uid = firebaseUser?.uid ?: ""
                        firestore.collection("users").document(user.uid!!).set(user)
                            .addOnSuccessListener {
                                _isRegistrationSuccessful.value = true
                            }.addOnFailureListener { e ->
                            _registrationErrorMessage.value = e.message
                        }
                    } else {
                        _registrationErrorMessage.value = task.exception?.message
                    }
                }
        }
    }

    private val _isLoginSuccessful = MutableStateFlow(false)
    val isLoginSuccessful: StateFlow<Boolean> = _isLoginSuccessful

    private val _loginErrorMessage = MutableStateFlow<String?>(null)
    val loginErrorMessage: StateFlow<String?> = _loginErrorMessage

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _isLoginSuccessful.value = true
                } else {
                    val errorMessage = when (val exception = task.exception) {
                        is FirebaseAuthException -> when (exception.errorCode) {
                            "ERROR_INVALID_EMAIL" -> "Email tidak valid."
                            "ERROR_USER_NOT_FOUND" -> "Email tidak ditemukan."
                            "ERROR_WRONG_PASSWORD" -> "Password salah."
                            "ERROR_USER_DISABLED" -> "Akun dinonaktifkan."
                            "ERROR_TOO_MANY_REQUESTS" -> "Terlalu banyak percobaan. Coba lagi nanti."
                            else -> "Kesalahan autentikasi."
                        }

                        else -> exception?.message ?: "Kesalahan tidak diketahui."
                    }
                    _loginErrorMessage.value = errorMessage
                }
            }
        }
    }
}

