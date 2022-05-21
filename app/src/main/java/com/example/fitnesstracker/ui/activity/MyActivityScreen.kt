package com.example.fitnesstracker.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.R
import com.example.fitnesstracker.data.CardsData
import com.example.fitnesstracker.domain.entiry.CardType
import com.example.fitnesstracker.ui.adapter.RecyclerViewAdapter

class MyActivityScreen : Fragment(R.layout.my_activity_screen) {

    private val activityData = CardsData()

    private val adapter = RecyclerViewAdapter {
        findNavController().navigate(
            ActivityScreenDirections.actionActivityScreenToWorkoutDetailsScreen(
                it.distance,
                it.period,
                it.typeActivity,
                it.dateActivity,
                it.cardType,
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.MyCards).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@MyActivityScreen.adapter
        }
        if (activityData.getDefaultData(CardType.my).isNotEmpty()) {
            adapter.setData(activityData.getDefaultData(CardType.my))
            view.findViewById<TextView>(R.id.text_one).visibility = View.GONE
            view.findViewById<TextView>(R.id.text_two).visibility = View.GONE
        }

    }

}