package org.d3if3104.myapplication.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.d3if3104.myapplication.firebase.UserRepository
import org.d3if3104.myapplication.model.User
import org.d3if3104.myapplication.navigation.Screen

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
                        saveUserToFirestore(user)
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
                            else -> "email or password doesn't match"
                        }
                        else -> exception?.message ?: "Kesalahan tidak diketahui."
                    }
                    _loginErrorMessage.value = errorMessage
                }
            }
        }
    }

    // Update user profile
    private val userRepository = UserRepository()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _role = MutableStateFlow<String>("")
    val role: StateFlow<String> = _role

    private val _updateSuccess = MutableStateFlow(false)
    val updateSuccess: StateFlow<Boolean> get() = _updateSuccess

    private val _updateError = MutableStateFlow<String?>(null)
    val updateError: StateFlow<String?> get() = _updateError

    private val _logoutSuccess = MutableStateFlow(false)
    val logoutSuccess: StateFlow<Boolean> = _logoutSuccess


    init {
        fetchCurrentUser()
    }

    private fun fetchCurrentUser() {
        val userId = userRepository.auth.currentUser?.uid
        userId?.let {
            fetchUserData(it)
        }
    }

    // Tambahkan ini di dalam class UserViewModel
    private val _userName = MutableStateFlow<String>("")
    val userName: StateFlow<String> = _userName

    // Update fetchUserData untuk menyimpan nama pengguna
    fun fetchUserData(userId: String) {
        viewModelScope.launch {
            try {
                val user = userRepository.getUserById(userId)
                _currentUser.value = user
                _role.value = user?.role ?: ""
                _userName.value = user?.name ?: ""
            } catch (e: Exception) {
                _updateError.value = e.message
                Log.d("UserViewModel", "Error fetching user data: ${e.message}")
            }
        }
    }


    fun update(name: String, email: String, address: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val userId = _currentUser.value?.uid ?: return
        val userDocRef = userRepository.firestore.collection("users").document(userId)

        viewModelScope.launch {
            try {
                val updates = hashMapOf<String, Any>(
                    "name" to name,
                    "email" to email,
                    "address" to address
                )
                userDocRef.update(updates).await()
                fetchUserData(userId) // Refresh current user data
                _updateSuccess.value = true
                _updateError.value = null
                Log.d("UserViewModel", "Berhasil Diubah.")
                onSuccess()
            } catch (e: Exception) {
                _updateError.value = e.message
                _updateSuccess.value = false
                Log.d("UserViewModel", "Gagal Mengubah: ${e.message}")
                onFailure(e)
            }
        }
    }

    fun resetUpdateState() {
        _updateSuccess.value = false
        _updateError.value = null
    }

    private fun saveUserToFirestore(user: User) {
        firestore.collection("users").document(user.uid)
            .set(user)
            .addOnSuccessListener {
                _isRegistrationSuccessful.value = true
            }
            .addOnFailureListener { e ->
                _registrationErrorMessage.value = e.message
                Log.e("UserViewModel", "Error saving user to Firestore: ${e.message}")
            }
    }

    fun resetLogoutState(){
        _logoutSuccess.value = false
    }

    fun logout(navController: NavHostController) {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Anda Telah Keluar")
                _logoutSuccess.value = true
                navController.navigate(Screen.Role.route) {
                    popUpTo(0) {inclusive = true }
                }
            } else {
                Log.d(TAG, "Gagal Keluar")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
}

