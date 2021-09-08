package com.octo.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.octo.workoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarExerciseActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        binding.toolbarExerciseActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.llRestView.setOnClickListener {
            setupRestView()
        }
    }

    override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        super.onDestroy()
    }

    private fun setRestProgressBar() {
        binding.progressBar.progress = restProgress
        restTimer = object : CountDownTimer(11000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding.progressBar.progress = 11 - restProgress
                binding.tvTimer.text = (11 - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@ExerciseActivity,
                    "Now we will start",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.start()
    }

    private fun setupRestView() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }
}