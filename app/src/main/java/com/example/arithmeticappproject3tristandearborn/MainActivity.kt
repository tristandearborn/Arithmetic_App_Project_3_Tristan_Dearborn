package com.example.arithmeticappproject3tristandearborn

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.arithmeticappproject3tristandearborn.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding //binding helps to call textViews and edit them
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intentTwo = intent //takes data from last activity
        val numQ = intentTwo.getStringExtra("numQ") //takes number of questions from last time playing
        val correct = intentTwo.getStringExtra("correct") //takes number of questions correct from last time playing
        var op = intentTwo.getStringExtra("op") //takes operation from last time playing
        var operation = "" //initialize a variable to add what operation was used into
        if (op == "2131230791") { //id for addition choice
            operation = "addition" //makes operation defined as addition
        } else if (op == "2131231176") { //id for subtraction choice
            operation = "subtraction" //makes operation defined as subtraction
        } else if (op == "2131231042") { //id for multiplication choice
            operation = "multiplication" //makes operation defined as multiplication
        } else { //if no operation choice was made or if division was chosen
            operation = "division" //makes operation defined as division
        }
        binding.score.text = ("Choose a difficulty and operation to practice!") //if there is no data for last time playing, meaning this is upon startup of the app
        if (correct != null && numQ != null) { //if there is data from last time playing, meaning if at least one round was played before
            if (correct.toDouble() / numQ.toDouble() >= 0.8) { //calculates percentage of correct answers and if it is above 80%,
                binding.score.text = ("Congratulations! You got $correct out of $numQ answers correct in $operation!") //sets the text to congratulate
            } else { //if percentage is not above 80%,
                binding.score.text = ("You got $correct out of $numQ answers correct in $operation. You need more practice!") //sets the text to tell to practice
            }
        }
    }

    fun plus(view: View) { //when plus button is clicked, adds a digit to the number of questions displayed
        var numStr = binding.numQ.text.toString() //gets the current number
        var num = numStr.toInt() //converts number to integer
        num += 1 //adds one
        numStr = num.toString() //converts back to string
        binding.numQ.text = numStr //displays in textview
    }

    fun minus(view: View) { //when minus button is clicked, subtracts a digit to the number of questions displayed
        var numStr = binding.numQ.text.toString() //gets the current number
        var num = numStr.toInt() //converts number to integer
        if (num > 1) { //if number is greater than one
            num -= 1 //subtracts one
        }
        numStr = num.toString() //converts back to string
        binding.numQ.text = numStr //displays in textview
    }

    fun start(view: View) { //when start button is clicked, will start the round of questions using the data collected and switching activities
        var numQ = binding.numQ.text.toString() //number of questions the user selected
        var difficultyID = binding.difficulty.checkedRadioButtonId.toString() //difficulty chosen by user
        var operationID = binding.operation.checkedRadioButtonId.toString() //operation chosen by user
        val intent = Intent(this, Actual::class.java) //creates intent to switch from this activity to actual gameplay activity
        intent.putExtra("numQ", numQ) //stores number of questions to transfer to gameplay activity
        intent.putExtra("difficultyID", difficultyID) //stores difficulty to transfer to gameplay activity
        intent.putExtra("operationID", operationID) //stores operation to transfer to gameplay activity
        startActivity(intent) //commits the transfer to next activity, with the data chosen by user


    }


}

