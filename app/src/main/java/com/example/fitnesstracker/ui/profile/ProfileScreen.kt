package com.example.fitnesstracker.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fitnesstracker.R
import com.example.fitnesstracker.network.SharedPref
import com.example.fitnesstracker.ui.ViewModelFactory
import com.example.fitnesstracker.ui.section.StartActivity
import com.google.android.material.textfield.TextInputEditText

class ProfileScreen : Fragment(R.layout.profile_screen) {

    private val viewModel by viewModels<ProfileScreenViewModel> {
        ViewModelFactory { ProfileScreenViewModel(SharedPref(requireContext())) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.login.observe(viewLifecycleOwner) {
            view.findViewById<TextInputEditText>(R.id.login).setText(it)
        }

        viewModel.name.observe(viewLifecycleOwner) {
            view.findViewById<TextInputEditText>(R.id.name).setText(it)
        }

        view.findViewById<Button>(R.id.button_logout).setOnClickListener {
            viewModel.onLogoutClicked()
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it == "Okay") {
                startActivity(Intent(activity, StartActivity::class.java))
                activity?.finish()
            } else if (it != "") {
                Toast.makeText(
                    requireActivity().application,
                    it,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}