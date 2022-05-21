package com.example.fitnesstracker.ui.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.R
import com.example.fitnesstracker.data.CardsData
import com.example.fitnesstracker.domain.entiry.CardType
import com.example.fitnesstracker.ui.adapter.RecyclerViewAdapter

class UsersActivityScreen : Fragment(R.layout.users_activity_screen) {
    private val activityData = CardsData()

    private val adapter = RecyclerViewAdapter {
        findNavController().navigate(
            ActivityScreenDirections.actionActivityScreenToWorkoutDetailsScreen(
                it.distance,
                it.period,
                it.typeActivity,
                it.dateActivity,
                it.cardType,
                it.nickname
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.UsersCards).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@UsersActivityScreen.adapter
        }
        adapter.setData(activityData.getDefaultData(CardType.users))
    }

}