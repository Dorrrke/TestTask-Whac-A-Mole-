package com.example.whac_a_mole.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.whac_a_mole.R
import kotlinx.coroutines.delay

class PlayFragment : Fragment(R.layout.fragment_play) {

    private var timer: CountDownTimer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var hole1 = view.findViewById<ImageView>(R.id.hole1)
        var hole2 = view.findViewById<ImageView>(R.id.hole2)
        var hole3 = view.findViewById<ImageView>(R.id.hole3)
        var hole4 = view.findViewById<ImageView>(R.id.hole4)
        var hole5 = view.findViewById<ImageView>(R.id.hole5)
        var hole6 = view.findViewById<ImageView>(R.id.hole6)
        var hole7 = view.findViewById<ImageView>(R.id.hole7)
        var hole8 = view.findViewById<ImageView>(R.id.hole8)
        var hole9 = view.findViewById<ImageView>(R.id.hole9)

        var holeList =
            listOf<ImageView>(hole1, hole2, hole3, hole4, hole5, hole6, hole7, hole8, hole9)

        startTimer(30000, holeList)
    }

    private fun startTimer(time: Long, list: List<ImageView>) {
        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(p0: Long) {
                view?.findViewById<TextView>(R.id.timer)?.text = "Timer: " + p0 / 1000 + "s"
            }

            override fun onFinish() {
                val toast = Toast.makeText(context, "Время кончилось", Toast.LENGTH_SHORT)
                toast.show()
                findNavController().navigate(R.id.action_playFragment_to_resultFragment, bundleOf(ResultFragment.scoreKey to view?.findViewById<TextView>(R.id.scoreValue)?.text.toString().toInt()))
            }
        }.start()

        object : CountDownTimer(time, 1000) {
            override fun onTick(p0: Long) {
                var index = (0..8).random()
                var temp = list[index]
                temp.setImageResource(R.drawable.mole)
                temp.tag = "mole"
                temp.isClickable = true
                temp.setOnClickListener {
                    if (temp.tag == "mole"){
                        temp.tag = "hole"
                        temp.setImageResource(R.drawable.hole)
                        var score = view?.findViewById<TextView>(R.id.scoreValue)?.text.toString().toInt()
                        view?.findViewById<TextView>(R.id.scoreValue)?.text = (score + 1).toString()
                        temp.isClickable = false
                    }
                    Log.e("KROT", index.toString())
                }
                view?.postDelayed(
                    {
                        list[index].setImageResource(R.drawable.hole)
                        temp.tag = "hole"
                        temp.isClickable = false
                    }, 500
                )
            }

            override fun onFinish() {
                Log.e("KROT", "END")
            }

        }.start()

    }
}