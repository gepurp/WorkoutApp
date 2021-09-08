package com.octo.workoutapp

object Constants {
    fun defaultExerciseList(): List<ExerciseModel> {
        val stretch = ExerciseModel(
            1,
            "Stretch",
            R.drawable.streching_image,
            isCompleted = false,
            isSelected = false
        )
        val run = ExerciseModel(
            2,
            "Run",
            R.drawable.running_image,
            isCompleted = false,
            isSelected = false
        )
        val fitness = ExerciseModel(
            3,
            "Fitness",
            R.drawable.fitness_image,
            isCompleted = false,
            isSelected = false
        )

        return listOf(stretch, run, fitness)
    }
}