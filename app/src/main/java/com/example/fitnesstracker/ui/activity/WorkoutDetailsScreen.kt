package com.example.fitnesstracker.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitnesstracker.R
import com.example.fitnesstracker.domain.entiry.CardType
import com.google.android.material.textfield.TextInputLayout

class WorkoutDetailsScreen : Fragment(R.layout.workout_details_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeCardActivity = CardType.values()[arguments?.getInt("CardType")!!]

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = arguments?.getString("TypeActivity")

        view.findViewById<TextView>(R.id.distance).text = arguments?.getString("Distance")
        view.findViewById<TextView>(R.id.dateActivity).text = arguments?.getString("DateActivity")
        view.findViewById<TextView>(R.id.period).text = arguments?.getString("Period")

        val nickname = view.findViewById<TextView>(R.id.nickname)
        nickname.text = arguments?.getString("Nickname")
        nickname.visibility = View.GONE;

        if (typeCardActivity == CardType.users) {
            val comment = view.findViewById<TextInputLayout>(R.id.comment)
            comment.isEnabled = false
            comment.isFocusable = false

            toolbar.menu.findItem(R.id.delete).isVisible = false
            toolbar.menu.findItem(R.id.share).isVisible = false
            nickname.visibility = View.VISIBLE;
        }

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}