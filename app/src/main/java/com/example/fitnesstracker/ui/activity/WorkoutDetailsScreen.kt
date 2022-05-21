package com.example.fitnesstracker.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitnesstracker.R
import com.example.fitnesstracker.domain.entiry.CardType
import com.google.android.material.textfield.TextInputLayout

class WorkoutDetailsScreen : Fragment(R.layout.workout_details_screen) {

    private val args by navArgs<WorkoutDetailsScreenArgs>()

    private val distance by lazy { args.distance }
    private val period by lazy { args.period }
    private val typeActivity by lazy { args.typeActivity }
    private val dateActivity by lazy { args.dateActivity }
    private val cardType by lazy { args.cardType }
    private val name by lazy { args.nickname }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = typeActivity

        view.findViewById<TextView>(R.id.distance).text = distance
        view.findViewById<TextView>(R.id.dateActivity).text = dateActivity
        view.findViewById<TextView>(R.id.period).text = period

        val nickname = view.findViewById<TextView>(R.id.nickname)
        nickname.text = name
        nickname.visibility = View.GONE;

        if (cardType == CardType.users) {
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