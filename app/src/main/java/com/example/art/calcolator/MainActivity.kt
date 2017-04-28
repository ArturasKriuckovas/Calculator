package com.example.art.calcolator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var globalFirstNumber: String = ""
    var globalSecondNumber: String = ""
    var globalOperationType: String = ""
    var globalResultNumber: String = ""
    val result: TextView by lazy { numbersDisplayer }
    val buttonZero: Button by lazy { zero }

    val OP_MAP = mapOf(
            "+" to {a: Double, b: Double -> a + b},
            "-" to {a: Double, b: Double -> a - b},
            "*" to {a: Double, b: Double -> a * b},
            "/" to {a: Double, b: Double -> a / b}
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonOne = one
        val buttonTwo = two
        val buttonThree = three
        val buttonFour = four
        val buttonFive = five
        val buttonSix = six
        val buttonSeven = seven
        val buttonEight = eight
        val buttonNine = nine
        val buttonDot = dot
        val buttonEquals = equals
        val buttonSubtract = subtract
        val buttonMultyply = multiply
        val buttonPlus = plus
        val buttonClear = clear
        val buttonMinus = minus


        buttonZero.setOnClickListener { View -> result.text = inputStringGetter("0") }
        buttonOne.setOnClickListener { View -> result.text = inputStringGetter("1") }
        buttonTwo.setOnClickListener { View -> result.text = inputStringGetter("2") }
        buttonThree.setOnClickListener { View -> result.text = inputStringGetter("3") }
        buttonFour.setOnClickListener { View -> result.text = inputStringGetter("4") }
        buttonFive.setOnClickListener { View -> result.text = inputStringGetter("5") }
        buttonSix.setOnClickListener { View -> result.text = inputStringGetter("6") }
        buttonSeven.setOnClickListener { View -> result.text = inputStringGetter("7") }
        buttonEight.setOnClickListener { View -> result.text = inputStringGetter("8") }
        buttonNine.setOnClickListener { View -> result.text = inputStringGetter("9") }
        buttonDot.setOnClickListener { dotInputting() } //View -> result.text =  inputStringGetter(".")
        buttonPlus.setOnClickListener { View -> operationDetectionAndExecution("+") }
        buttonMinus.setOnClickListener { View -> operationDetectionAndExecution("-") }
        buttonMultyply.setOnClickListener { View -> operationDetectionAndExecution("*") }
        buttonSubtract.setOnClickListener { View -> operationDetectionAndExecution("/") }
        buttonClear.setOnClickListener { View -> operationDetectionAndExecution("clear") }
        buttonEquals.setOnClickListener { answerGetter(); clearEveryThing(true, "") }
    }

    fun operationDetectionAndExecution(operationType: String) {
        when (operationType) {
            "clear" -> { clearEveryThing(false, "cleared") }
            else -> {
                globalOperationType = operationType; result.text = globalOperationType
                if (globalFirstNumber == "" || (globalFirstNumber != "" && globalSecondNumber != "")) {
                    clearEveryThing(false, "error")
                }
            }
        }
    }

    fun inputStringGetter(element: String): String {
        var result: String = "" //gali erroras del to but
        if (globalOperationType == "") {

            globalFirstNumber += element
                result = globalFirstNumber

        } else {
            globalSecondNumber += element
                result = globalSecondNumber
            }


        return result
    }

    fun answerGetter() {
        if (globalFirstNumber == "" || globalSecondNumber == "") {
            result.text = "error"
        } else {
            val a = globalFirstNumber.toDouble()
            val b = globalSecondNumber.toDouble()
            result.text = OP_MAP[globalOperationType]?.invoke(a, b).toString()
        }
    }

    fun checkIfTwoDots(number: String): Boolean {
        val numberOfDots: Int = number.split(".").count()
        val validNumber: Boolean
        if (numberOfDots > 1) {
            validNumber = false
        } else {
            validNumber = true
        }
        return validNumber
    }

    fun clearEveryThing(isOperationEqual: Boolean, msg: String) {
        if (isOperationEqual == false) {
            globalOperationType = ""
            globalFirstNumber = ""
            globalSecondNumber = ""
            globalResultNumber = ""
            result.text = msg
        } else {
            globalOperationType = ""
            globalFirstNumber = ""
            globalSecondNumber = ""
            globalResultNumber = ""
        }

    }

    fun dotInputting() {
        if (globalOperationType.equals("")) {
            if (checkIfTwoDots(globalFirstNumber) == true) {
                globalFirstNumber = globalFirstNumber + "."
                result.text = globalFirstNumber
            }else if (checkIfTwoDots(globalFirstNumber) == false){
                result.text = globalFirstNumber
            }
        } else {
            if (checkIfTwoDots(globalSecondNumber) == true) {
                globalSecondNumber = globalSecondNumber + "."
                result.text = globalSecondNumber
            }else if (checkIfTwoDots(globalSecondNumber) == false){
                result.text = globalSecondNumber
            }

        }
    }
}