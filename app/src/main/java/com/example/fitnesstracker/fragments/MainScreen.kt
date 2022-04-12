package com.example.fitnesstracker.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitnesstracker.R

class MainScreen : Fragment(R.layout.main_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_registrationScreen)
        }

        view.findViewById<TextView>(R.id.text_log_in).setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_loginScreen)
        }

    }
}