package com.example.arithmeticappproject3tristandearborn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.arithmeticappproject3tristandearborn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding //binding helps to call textViews and edit them
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    fun plus(view: View) { //when plus button is clicked
        var numStr = binding.numQ.text.toString() //gets the current number
        var num = numStr.toInt() //converts number to integer
        num+=1 //adds one
        numStr = num.toString() //converts back to string
        binding.numQ.text=numStr //displays in textview
    }
    fun minus(view: View) { //when minus button is clicked
        var numStr = binding.numQ.text.toString()
        var num = numStr.toInt()
        if (num > 1){ //if number is greater than one
            num-=1 //subtracts one
        }
        numStr = num.toString()
        binding.numQ.text=numStr
    }

    fun start(view: View) { //when start button is clicked
        var numQ = binding.numQ.text.toString() //number of Questions
        var difficultyID = binding.difficulty.checkedRadioButtonId.toString() //difficulty chosen
        var operationID = binding.operation.checkedRadioButtonId.toString() //operation chosen
        val intent = Intent(this, Actual::class.java) //creates intent to switch from this activity to actual gameplay activity
        intent.putExtra("numQ", numQ) //stores number of questions to transfer to gameplay activity
        intent.putExtra("difficultyID", difficultyID) //stores difficulty to transfer to gameplay activity
        intent.putExtra("operationID", operationID) //stores operation to transfer to gameplay activity
        startActivity(intent) //transfers to next activity


    }


}

