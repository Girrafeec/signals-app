/* Created by Girrafeec */

package com.girrafeecstud.signals.core_base.base

import java.text.SimpleDateFormat
import java.util.*

fun Double.roundTo(numDecimalPlaces: Int): Double =
    "%.${numDecimalPlaces}f".format(this).replace(',', '.').toDouble()

fun Long.milliesToFormattedTimeString(): String {
    val hours = this / (60 * 60 * 1000) % 24
    val minutes = this / (60 * 1000) % 60
    val seconds = this / 1000 % 60
    val milliseconds = this % 1000
    return when {
        hours > 0 -> {
            if (milliseconds > 0) {
                String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds)
            } else {
                String.format("%02d:%02d:%02d", hours, minutes, seconds)
            }
        }
        minutes > 0 -> {
            if (milliseconds > 0) {
                String.format("%02d:%02d.%03d", minutes, seconds, milliseconds)
            } else {
                String.format("%02d:%02d", minutes, seconds)
            }
        }
        seconds > 0 -> {
            if (milliseconds > 0) {
                String.format("%02d.%03d", seconds, milliseconds)
            } else {
                String.format("%02d", seconds)
            }
        }
        else -> {
            if (milliseconds > 0) {
                String.format("00.%03d", milliseconds)
            } else {
                "00"
            }
        }
    }
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}