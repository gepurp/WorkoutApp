package com.octo.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.octo.workoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding
    private var restTimer: CountDownTimer? = null
    private var exerciseTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exerciseProgress = 0

    private var exercises: List<ExerciseModel>? = null
    private var currentPos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exercises = Constants.defaultExerciseList()

        binding.tvTimerName.text = "CLICK TO START"

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
        restTimer?.cancel()
        restProgress = 0
        exerciseTimer?.cancel()
        exerciseProgress = 0
        super.onDestroy()
    }

    private fun setRestProgressBar() {
        binding.progressBar.apply {
            max = 10
            progress = restProgress
        }
        binding.tvTimerName.text = "GET READY"
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
                currentPos++
                setupExerciseView()
            }
        }.start()
    }

    private fun setExerciseProgressBar() {
        binding.progressBar.apply {
            max = 30
            progress = exerciseProgress
        }
        binding.tvTimerName.text = "EXERCISE NAME"
        exerciseTimer = object : CountDownTimer(31000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding.progressBar.progress = 31 - exerciseProgress
                binding.tvTimer.text = (31 - exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@ExerciseActivity,
                    "Now rest a while",
                    Toast.LENGTH_SHORT
                ).show()
                setupRestView()
            }
        }.start()
    }

    private fun setupRestView() {
        restTimer?.cancel()
        restProgress = 0
        setRestProgressBar()
    }

    private fun setupExerciseView() {
        exerciseTimer?.cancel()
        exerciseProgress = 0
        setExerciseProgressBar()
        if (currentPos < exercises!!.size) {
            binding.ivExerciseImage.setImageResource(exercises?.get(currentPos)!!.image)
            binding.tvTimerName.text = exercises?.get(currentPos)!!.name
        } else {
            finish()
        }
    }
}