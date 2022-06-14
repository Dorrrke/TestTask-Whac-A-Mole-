package com.example.whac_a_mole.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
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
import kotlin.properties.Delegates

class ResultFragment : Fragment(R.layout.fragment_result) {
    companion object {
        const val scoreKey = "SCORE"
    }

    @Inject
    lateinit var db : AppDatabase
    var scoreValue: Int? = null
    var highScore: Record? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MainApp).appComponent.inject(this)
        scoreValue = arguments?.get(scoreKey) as Int
        highScore = db.RecordDao().getRecord()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.resultScore).text = scoreValue.toString()
        if (highScore == null){
            view.findViewById<TextView>(R.id.recordScore).text = 0.toString()
            newRecord(scoreValue!!)
        }
        else{
            view.findViewById<TextView>(R.id.recordScore).text = highScore!!.record.toString()
            if( scoreValue!! > highScore!!.record){
                updateRecord(scoreValue!!, highScore!!)
            }
        }

        var againBtn = view.findViewById<Button>(R.id.againBtn)
        var menuBtn = view.findViewById<Button>(R.id.menuBtn)
        againBtn.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_playFragment)
        }
        menuBtn.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_mainMenuFragment)
        }

    }
    private fun updateRecord(scoreValue: Int, highScore: Record) {
        db.RecordDao().update(Record(highScore!!.RecordId, scoreValue!!))
        view?.findViewById<TextView>(R.id.recordScore)!!.text = scoreValue.toString()
    }
    private fun newRecord(scoreValue: Int) {
        db.RecordDao().insert(Record(0, scoreValue!!))
        view?.findViewById<TextView>(R.id.recordScore)!!.text = scoreValue.toString()
    }
}