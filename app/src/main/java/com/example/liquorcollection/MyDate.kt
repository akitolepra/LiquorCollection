package com.example.liquorcollection

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MyDate(val year: Int = -1, month: Int = -1, day: Int = -1) {
    private val date = LocalDateTime.now()

    fun getCurrentDateInEightDigitWithSlash(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        return date.format(formatter)
    }
    fun getCurrentTime(): String {
        val formatter = DateTimeFormatter.ofPattern("h:mm a")
        return date.format(formatter)
    }
}