package com.example.arithmeticappproject3tristandearborn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.arithmeticappproject3tristandearborn.databinding.ActivityFinishScreenBinding

class finishScreen : AppCompatActivity() {
    private lateinit var binding: ActivityFinishScreenBinding //binding helps to call textViews and edit them
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intentTwo = intent //takes data from last activity
        val numQ = intentTwo.getStringExtra("numQ") //takes number of questions from last activity
        val correct = intentTwo.getStringExtra("correct") //takes number of questions correct from last activity
        var score = "Congratulations! You got $correct questions correct out of $numQ!" //creates final score
        binding.amountCorrect.text = score //displays final score
    }

    fun playAgain(view: View) { //when play again is pressed
        val intent = Intent(this, MainActivity::class.java) //creates intent to go back to beginning screen
        startActivity(intent) //goes back to beginning screen

    }
}