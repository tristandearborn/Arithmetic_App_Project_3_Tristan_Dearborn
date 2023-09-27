package com.example.arithmeticappproject3tristandearborn

import android.R.raw
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.arithmeticappproject3tristandearborn.databinding.ActivityActualBinding
import com.example.arithmeticappproject3tristandearborn.databinding.ActivityMainBinding
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
        val intent = intent //creates intent from previous activity to transfer previous data over
        makeProb() //calls makeProb function to create a problem to solve

    }

    var correct = 0 //initializes counter of how many correct answers
    var counter = 0 //initializes counter of how many questions have been answered

    fun makeProb() { //creates a problem to be displayed for the user to solve, using the input from the user for difficulty, operation, and number of questions
        val difficultyID = intent.getStringExtra("difficultyID") //gets difficulty chosen from previous activity
        val operationID = intent.getStringExtra("operationID") //gets operation chosen from previous activity
        val numQ = intent.getStringExtra("numQ") //gets number of questions chosen from previous activity
        var randOne: Int //creates a variable for a random number one to be used in operation
        var randTwo: Int //creates a variable for a random number two to be used in operation
        var operation: String //creates a variable for the operation to be put into
        if (difficultyID == "2131230894") { //if this id, for easy button choice, was chosen by user in previous activity
            randOne = Random.nextInt(0, 10) //random number generator from 0 to 10 for the first number used in operation
            randTwo = Random.nextInt(0, 10) //random number generator from 0 to 10 for the second number used in operation
        } else if (difficultyID == "2131231007") { //if this id, for medium button choice, was chosen by user in previous activity
            randOne = Random.nextInt(0, 25) //random number generator from 0 to 25 for the first number used in operation
            randTwo = Random.nextInt(0, 25) //random number generator from 0 to 25 for the second number used in operation
        } else { //if neither easy or medium was chosen, so if hard was chosen or if no choice was made at all
            randOne = Random.nextInt(0, 50) //random number generator from 0 to 50 for the first number used in operation
            randTwo = Random.nextInt(0, 50) //random number generator from 0 to 50 for the second number used in operation
        }
        if (operationID == "2131230791") { //if this id, for addition button choice, was chosen by user in previous activity
            operation = "+" //defines the operation to be used as addition
        } else if (operationID == "2131231176") { //if this id, for subtraction button choice, was chosen by user in previous activity
            operation = "-" //defines the operation to be used as subtraction
            if (difficultyID == "2131230894") { //if this id, for easy button choice, was chosen by user in previous activity
                randOne = Random.nextInt(7, 10) //high number to be subtracted into
                randTwo = Random.nextInt(0, 7) //low number to subtract by, as for the answer to not be negative
            } else if (difficultyID == "2131231007") { //if this id, for medium button choice, was chosen by user in previous activity
                randOne = Random.nextInt(15, 25) //high number to be subtracted into
                randTwo = Random.nextInt(0, 15) //low number to subtract by, as for the answer to not be negative
            } else { //if neither easy or medium was chosen, so if hard was chosen or if no choice was made at all
                randOne = Random.nextInt(35, 50) //high number to be subtracted into
                randTwo = Random.nextInt(0, 35) //low number to subtract by, as for the answer to not be negative
            }
        } else if (operationID == "2131231042") { //if this id, for multiplication button choice, was chosen by user in previous activity
            operation = "*" //defines the operation to be used as multiplication
        } else { //if no operation choice was made or if division was chosen by user
            operation = "/" //defines the operation to be used as division
            if (randTwo == 0){ //if the denominator is 0, which is impossible to divide into
                if (difficultyID == "2131230894") { //if this id, for easy button choice, was chosen by user in previous activity
                    randTwo = Random.nextInt(1, 10) //random number generator from 1 to 10 for easy choice, used as the denominator. doesn't allow for 0 to be a denominator, as that is impossible to divide into.
                    randOne = Random.nextInt(randTwo, 10) //random number generator from whatever the denominator is to 10, for easy choice, used as numerator, to keep the answer at least 1, as program only rounds to nearest integer for the answer
                } else if (difficultyID == "2131231007") { //if this id, for medium button choice, was chosen by user in previous activity
                    randTwo = Random.nextInt(1, 25) //random number generator from 1 to 25 for medium choice, used as the denominator. doesn't allow for 0 to be a denominator, as that is impossible to divide into.
                    randOne = Random.nextInt(randTwo, 25) //random number generator from whatever the denominator is to 25, for medium choice, used as numerator, to keep the answer at least 1, as program only rounds to nearest integer for the answer
                } else { //if neither easy or medium was chosen, so if hard was chosen or if no choice was made at all
                    randTwo = Random.nextInt(1, 50) //random number generator from 1 to 50 for hard choice, used as the denominator. doesn't allow for 0 to be a denominator, as that is impossible to divide into.
                    randOne = Random.nextInt(randTwo, 50) //random number generator from whatever the denominator is to 50, for hard choice, used as numerator, to keep the answer at least 1, as program only rounds to nearest integer for the answer
                }
            }
        }
        var strEq = ("$randOne$operation$randTwo") //creates operation from random number one, operation, and random number two
        bindingSecond.equation.text = strEq //displays question for the user to answer
    }

    fun submitAns(view: View) { //when submit button is clicked, checks the answer based on the user's input
        checkAns() //calls the checkAns function to check the user's input based on the correct answer
    }

    fun checkAns() { //solves the created question and checks the true answer against the user's inputted answer to determine if they were correct or not
        val inputAns = bindingSecond.clickAns.text.toString() //retrieves the user's inputted answer and saves it as a variable
        val question = bindingSecond.equation.text.toString() //retrieves the question that was asked to the user and saves it as a variable
        val numQ = intent.getStringExtra("numQ") //retrieves the total number of questions the user inputted from the previous activity
        val numQInt = numQ?.toInt() //converts total number of questions to int
        val operationID = intent.getStringExtra("operationID") //retrieves the operation the user inputted from the previous activity
        var correctAns = "" //creates variable for correct answer to occupy
        for (i in question.indices) { // iterates through the total length of the string, through each character
            if (question[i] == '*' || question[i] == '-' || question[i] == '+' || question[i] == '/') { //finds out when an operation is used in string from when the character is an operation character
                var numOne = "" //initializes an empty string to add first numbers into
                var numTwo = "" //initializes an empty string to add second numbers into
                for (j in 0..<i) { //iterates through the beginning of the string to then where in the string an operation appears, to isolate the first number
                    numOne += question[j] //as each number is iterated through, adds them to the first number string to create the first number
                }
                for (k in i + 1..<question.length) { //iterates through after an operation appears to the end of the string, isolating the second number
                    numTwo += question[k] //as each number is iterated through, adds them to the second number string to create the second number
                }
                if (question[i] == '+') { //if the operation in the string is addition, solves the problem
                    var numOneNum = numOne.toInt() //converts numOne to an int
                    var numTwoNum = numTwo.toInt() //converts numTwo to an int
                    var ans = numOneNum + numTwoNum //adds the numbers together for the answer
                    correctAns = ans.toString() //turns answer to a string
                }
                if (question[i] == '-') { //if the operation in the string is subtraction, solves the problem
                    var numOneNum = numOne.toInt() //converts numOne to an int
                    var numTwoNum = numTwo.toInt() //converts numTwo to an int
                    var ans = numOneNum - numTwoNum //subtracts the numbers together for the answer
                    correctAns = ans.toString() //turns answer to string
                }
                if (question[i] == '*') { //if the operation in the string is multiplication, solves the problem
                    var numOneNum = numOne.toInt() //converts numOne to an int
                    var numTwoNum = numTwo.toInt() //converts numTwo to an int
                    var ans = numOneNum * numTwoNum //multiplies the numbers together for the answer
                    correctAns = ans.toString() //turns answer to string
                }
                if (question[i] == '/') { //if the operation in the string is division, solves the problem
                    var numOneNum = numOne.toInt() //converts numOne to an int
                    var numTwoNum = numTwo.toInt() //converts numTwo to an int
                    var ans = numOneNum / numTwoNum //divides the numbers for the answer
                    ans = ans.toDouble().roundToInt() //rounds to nearest whole number for the answer
                    correctAns = ans.toString() //turns answer to string
                }

            }

        }
        var correctTone = MediaPlayer.create(this, R.raw.correct) //initializes a variable to play a correct tone from raw folder
        var incorrectTone = MediaPlayer.create(this, R.raw.incorrect) //initializes a variable to play an incorrect tone from raw folder
        if (correctAns == inputAns) { //if the user's inputted answer matches correct answer
            Toast.makeText(this, "Correct. Good work!", Toast.LENGTH_SHORT).show() //displays a message congratulating user on answering correctly
            correct += 1 //correct answer counter up one
            correctTone.start() //a correct tone is played for the user
        } else { //if the user's inputted answer does not match the correct answer
            Toast.makeText(this, "Wrong.", Toast.LENGTH_SHORT).show() //displays a message telling the user their answer was wrong
            incorrectTone.start() //an incorrect tone is played for the user

        }
        counter += 1 //after every question, counter goes up one to count how many questions have been answered so far
        if (counter == numQInt) { //if the counter is equal to the total number of questions selected by the user
            var correctStr = correct.toString() //converts the number of correct answers to a string to carry over to the original activity
            val intentTwo = Intent(this, MainActivity::class.java) //creates intent to switch to original activity
            intentTwo.putExtra("numQ", numQ) //stores number of questions to transfer back to the original activity
            intentTwo.putExtra("correct", correctStr) //stores the number of questions the user answered correctly to transfer back to the original activity
            intentTwo.putExtra("op", operationID) //stores the operation the user selected to transfer back to the original activity
            startActivity(intentTwo) //commits the transfer to the original activity, with the data of the user's correct answers, operation chosen by the user, and number of questions chosen by the user
        }
        else { //if the number of questions answered does not equal the total number of questions to be displayed yet
            bindingSecond.clickAns.setText("") //makes the textview displaying the problem blank, to generate a new problem in
            makeProb() //calls the makeProb function to generate a new problem for the user to solve
        }
    }
}
