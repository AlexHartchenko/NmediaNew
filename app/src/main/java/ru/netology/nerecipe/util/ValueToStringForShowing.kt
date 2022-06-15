package ru.netology.nerecipe

import java.text.DecimalFormat
import kotlin.math.abs

fun valueToStringForShowing (value: Int): String {
    val dF = DecimalFormat("##################################################.#")
    return when {
        abs(value) in 1000..9999 ->
            (dF.format((((value.toDouble()/100).toInt()).toDouble())/10)) + "K"
        abs(value) in 10000..999999 -> ((value.toDouble()/1000).toInt()).toString() + "K"
        abs(value) >= 1000000 ->
            (dF.format((((value.toDouble()/100000).toInt()).toDouble())/10)) + "M"
        else -> value.toString()
    }
}

