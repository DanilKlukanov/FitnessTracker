package com.example.fitnesstracker.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnesstracker.network.SharedPref
import com.example.fitnesstracker.network.dto.User
import com.example.fitnesstracker.network.service.Service

class ProfileScreenViewModel(private val sharedPref: SharedPref) : ViewModel()  {

    private val service = Service()

    private val _login = MutableLiveData<String>("")
    private val _name = MutableLiveData<String>("")
    private val _error = MutableLiveData<String>("")

    val login: LiveData<String> get() = _login
    val name: LiveData<String> get() = _name
    val error: LiveData<String> get() = _error

    private fun getProfileInfo() {
        val token = "Bearer ".plus(sharedPref.getToken())

        service.profile(token, object : Service.ProfileCallback {
            override fun onSuccess(result: User) {
                _login.value = result.login
                _name.value = result.name
            }

            override fun onError(error: Throwable) {
                _login.value = ""
                _name.value = ""
            }
        })
    }

    fun onLogoutClicked() {
        val token = "Bearer ".plus(sharedPref.getToken())

        service.logout(token, object : Service.LogoutCallback {
            override fun onSuccess(result: String) {
                sharedPref.cleanToken()
                _error.value = "Okay"
            }

            override fun onError(error: Throwable) {
                _error.value = error.toString()
            }
        })
    }

    init {
        getProfileInfo()
    }
}