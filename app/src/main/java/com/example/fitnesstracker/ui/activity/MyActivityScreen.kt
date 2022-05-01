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
        val bundle = Bundle()
        bundle.putString("Distance", it.distance)
        bundle.putString("Period", it.period)
        bundle.putString("TypeActivity", it.typeActivity)
        bundle.putString("DateActivity", it.dateActivity)
        bundle.putInt("CardType", it.cardType.ordinal)

        findNavController().navigate(
            R.id.action_activityScreen_to_workoutDetailsScreen,
            bundle
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