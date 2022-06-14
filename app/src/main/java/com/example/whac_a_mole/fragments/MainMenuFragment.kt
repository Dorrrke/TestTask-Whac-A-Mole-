package com.example.whac_a_mole.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.whac_a_mole.MainApp
import com.example.whac_a_mole.R
import com.example.whac_a_mole.database.AppDatabase
import com.example.whac_a_mole.database.entity.Record
import javax.inject.Inject


class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {

    @Inject
    lateinit var db : AppDatabase
    var highScore: Record? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MainApp).appComponent.inject(this)
        highScore = db.RecordDao().getRecord()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var playBtn = view.findViewById<Button>(R.id.playBtn)

        if (highScore == null) {
            view.findViewById<TextView>(R.id.currentRecord).text = 0.toString()
        }
        else{
            view.findViewById<TextView>(R.id.currentRecord).text = highScore!!.record.toString()
        }

        playBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_playFragment)
        }

    }

}