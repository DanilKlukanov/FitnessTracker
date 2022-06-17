package com.example.fitnesstracker.ui.start.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitnesstracker.ui.section.MainActivity
import com.example.fitnesstracker.R
import com.example.fitnesstracker.network.SharedPref
import com.example.fitnesstracker.ui.ViewModelFactory
import com.example.fitnesstracker.ui.profile.ProfileScreenViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginScreen : Fragment(R.layout.login_screen) {

    private val viewModel by viewModels<LoginScreenViewModel> {
        ViewModelFactory { LoginScreenViewModel(SharedPref(requireContext())) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.showLoginError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputLayout>(R.id.LoginInput).error = "Введите логин"
            } else {
                view.findViewById<TextInputLayout>(R.id.LoginInput).error = null
            }
        }

        viewModel.showPasswordError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputLayout>(R.id.PasswordInput).error = "Введите пароль"
            } else {
                view.findViewById<TextInputLayout>(R.id.PasswordInput).error = null
            }
        }


        view.findViewById<Button>(R.id.BtnLogin).setOnClickListener {
            viewModel.onLoginClicked(
                view.findViewById<TextInputEditText>(R.id.login).text.toString(),
                view.findViewById<TextInputEditText>(R.id.password).text.toString()
            )
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it == "Okay") {
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            } else {
                Toast.makeText(
                    requireActivity().application,
                    it,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}