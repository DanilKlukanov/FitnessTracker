package com.example.fitnesstracker.ui.start.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnesstracker.network.SharedPref
import com.example.fitnesstracker.network.dto.Login
import com.example.fitnesstracker.network.service.Service

class RegistrationScreenViewModel(private val sharedPref: SharedPref) : ViewModel() {

    private val service = Service()

    private val _showLoginError = MutableLiveData<Boolean>(false)
    private val _showNameError = MutableLiveData<Boolean>(false)
    private val _showPasswordError = MutableLiveData<Boolean>(false)
    private val _showRepeatPasswordError = MutableLiveData<Boolean>(false)
    private val _showWrongRepeatPasswordError = MutableLiveData<Boolean>(false)
    private val _error = MutableLiveData<String>("")

    val showLoginError: LiveData<Boolean> get() = _showLoginError
    val showNameError: LiveData<Boolean> get() = _showNameError
    val showPasswordError: LiveData<Boolean> get() = _showPasswordError
    val showRepeatPasswordError: LiveData<Boolean> get() = _showRepeatPasswordError
    val showWrongRepeatPasswordError: LiveData<Boolean> get() = _showWrongRepeatPasswordError
    val error: LiveData<String> get() = _error

    fun onRegistrationClicked(
        login: String,
        name: String,
        password: String,
        passwordRepeat: String,
        gender: Int
    ) {
        if (login.isBlank()) {
            _showLoginError.postValue(true)
        }

        if (name.isBlank()) {
            _showNameError.postValue(true)
        }

        if (password.isBlank()) {
            _showPasswordError.postValue(true)
        }

        if (passwordRepeat.isBlank()) {
            _showRepeatPasswordError.postValue(true)
        }

        if (passwordRepeat != password) {
            _showWrongRepeatPasswordError.postValue(true)
        }

        service.register(
            login,
            password,
            name,
            gender,
            object : Service.LoginCallback {
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