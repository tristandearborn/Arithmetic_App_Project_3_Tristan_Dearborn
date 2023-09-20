package com.example.arithmeticappproject3tristandearborn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.arithmeticappproject3tristandearborn.databinding.ActivityMainBinding
import com.example.arithmeticappproject3tristandearborn.databinding.ActivityActualBinding
import java.lang.Math.round
import kotlin.math.roundToInt
import kotlin.random.Random

class Actual : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding //binding helps to call textViews and edit them
    private lateinit var bindingSecond: ActivityActualBinding //binding helps to call textViews and edit them
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingSecond = ActivityActualBinding.inflate(layoutInflater)
        val view = binding.root
        val viewSecond = bindingSecond.root
        setContentView(view)
        setContentView(viewSecond)
        val intent = intent //creates intent from previous activity
        val difficultyID = intent.getStringExtra("difficultyID") //gets difficulty chosen from previous activity
        val operationID = intent.getStringExtra("operationID") //gets operation chosen from previous activity
        val numQ = intent.getStringExtra("numQ") //gets number of questions chosen from previous activity
        makeProb() //calls makeProb function to create a problem to solve

    }

    var correct = 0 //counter of how many correct answers
    var counter = 0 //counter of how many questions answered

    fun makeProb() { //creates a problem
        val difficultyID = intent.getStringExtra("difficultyID") //gets difficulty chosen from previous activity
        val operationID = intent.getStringExtra("operationID") //gets operation chosen from previous activity
        val numQ = intent.getStringExtra("numQ") //gets number of questions chosen from previous activity
        var randOne: Int //creates a variable for a random number one
        var randTwo: Int //creates a variable for a random number two
        var operation: String //creates a variable for the operation
        if (difficultyID == "2131230896") { //id for easy button choice
            randOne = Random.nextInt(0, 10) //random number generator from 0 to 10
            randTwo = Random.nextInt(0, 10) //random number generator from 0 to 10
        } else if (difficultyID == "2121231011") { //id for medium button choice
            randOne = Random.nextInt(0, 25) //random number generator from 0 to 25
            randTwo = Random.nextInt(0, 25) //random number generator from 0 to 25
        } else { //if neither easy or medium was chosen, so if hard was chosen or none at all
            randOne = Random.nextInt(0, 50) //random number generator from 0 to 50
            randTwo = Random.nextInt(0, 50) //random number generator from 0 to 50
        }
        if (operationID == "2131230791") { //id for addition choice
            operation = "+"
        } else if (operationID == "2131231179") { //id for subtraction choice
            operation = "-"
            if (difficultyID == "2131230896") { //id for easy choice
                randOne = Random.nextInt(7, 10) //high number
                randTwo = Random.nextInt(0, 7) //low number to subtract by, so no negatives
            } else if (difficultyID == "2131231011") { //id for medium choice
                randOne = Random.nextInt(15, 25) //high number
                randTwo = Random.nextInt(0, 15) //low number to subtract by, so no negatives
            } else { //if neither easy or medium was chosen, so if hard was chosen or none at all
                randOne = Random.nextInt(35, 50) //high number
                randTwo = Random.nextInt(0, 35) //low number to subtract by, so no negatives
            }
        } else if (operationID == "2131231046") { //id for multiplication choice
            operation = "*"
        } else { //if no operation choice was made or if division was chosen
            operation = "/"
        }
        var strEq = ("$randOne$operation$randTwo") //creates operation from random number one, operation, and random number two
        bindingSecond.equation.text = strEq //displays question
    }

    fun submitAns(view: View) { //when submit button is clicked
        checkAns() //checks answer
    }

    fun checkAns() {
        var inputAns = bindingSecond.clickAns.toString() //gets what the user answered
        var question = bindingSecond.equation.text.toString() //gets what the question was
        var numQ = intent.getStringExtra("numQ") //gets total number of questions
        var numQInt = numQ?.toInt() //converts number of questions to int
        var correctAns = "" //creates variable for correct answer to occupy
        for (i in 0..question.length - 1) { // for the total length of the string
            if (question[i] == '*' || question[i] == '-' || question[i] == '+' || question[i] == '/') { //finds when an operation is used in string
                var numOne = "" //empty string to add numbers into
                var numTwo = "" //empty string to add numbers into
                for (j in 0..i - 1) { //for beginning of string to when operation is used in string
                    numOne += question[j] //adds everything from before the operation to a string, numOne
                }
                for (k in i + 1..question.length - 1) { //for after operation is used to end of string
                    numTwo += question[k] //adds everything from after operation to a string, numTwo
                }
                if (question[i] == '+') { //if addition
                    var numOneNum = numOne.toInt() //converts numOne to an int
                    var numTwoNum = numTwo.toInt() //converts numTwo to an int
                    var ans = numOneNum + numTwoNum //adds together
                    correctAns = ans.toString() //turns answer to string
                }
                if (question[i] == '-') { //if minus
                    var numOneNum = numOne.toInt() //converts numOne to an int
                    var numTwoNum = numTwo.toInt() //converts numTwo to an int
                    var ans = numOneNum - numTwoNum //subtracts numbers
                    correctAns = ans.toString() //turns answer to string
                }
                if (question[i] == '*') { //if multiplication
                    var numOneNum = numOne.toInt() //converts numOne to an int
                    var numTwoNum = numTwo.toInt() //converts numTwo to an int
                    var ans = numOneNum * numTwoNum //multiplies together
                    correctAns = ans.toString() //turns answer to string
                }
                if (question[i] == '/') { //if division
                    var numOneNum = numOne.toInt() //converts numOne to an int
                    var numTwoNum = numTwo.toInt() //converts numTwo to an int
                    var ans = numOneNum / numTwoNum //divides numbers
                    ans = ans.toDouble().roundToInt() //rounds to nearest whole number
                    correctAns = ans.toString() //turns answer to string
                }

            }

        }

        if (correctAns == inputAns){ //if user's answer matches correct answer
            correct+=1 //correct counter up one
        }
        counter+=1 //after every question, counter goes up one
        if (counter == numQInt){ //when counter is equal to number of questions
            var correctStr = correct.toString() //converts number of correct answers to string
            val intentTwo = Intent(this, finishScreen::class.java) //creates intent to switch to final screen
            intentTwo.putExtra("numQ", numQ) //stores total number of questions to take to final screen
            intentTwo.putExtra("correct", correctStr) //stores total number of questions correct to take to final screen
            startActivity(intentTwo) //switches to final screen
        }
        else{ //if counter hasn't been hit yet
            bindingSecond.clickAns.setText("") //texview becomes blank
            makeProb() //makes another problem
        }
    }
}
