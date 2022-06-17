package com.example.fitnesstracker.ui.start.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnesstracker.network.SharedPref
import com.example.fitnesstracker.network.dto.Login
import com.example.fitnesstracker.network.service.Service

class LoginScreenViewModel(private val sharedPref: SharedPref) : ViewModel() {

    private val service = Service()

    private val _showLoginError = MutableLiveData<Boolean>(false)
    private val _showPasswordError = MutableLiveData<Boolean>(false)
    private val _error = MutableLiveData<String>("")

    val showLoginError: LiveData<Boolean> get() = _showLoginError
    val showPasswordError: LiveData<Boolean> get() = _showPasswordError
    val error: LiveData<String> get() = _error

    fun onLoginClicked(login: String, password: String) {
        if (login.isBlank()) {
            _showLoginError.postValue(true)
        }

        if (password.isBlank()) {
            _showPasswordError.postValue(true)
        }

        service.login(login, password, object : Service.LoginCallback {
            override fun onSuccess(result: Login) {
                sharedPref.saveToken(result.token)
                _error.value = "Okay"
            }

            override fun onError(error: Throwable) {
                _error.value = error.toString()
            }
        })
    }
}