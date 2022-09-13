package com.bakharaalief.graphqlappic.util

import java.text.SimpleDateFormat
import java.util.*

object Helper {

    fun isAccessExpired(date: String): Boolean {
        val tokenDate: Date = stringToDate(date)
        val tokenDateExpired = tokenDate.time.plus(25200000)
        return tokenDateExpired < (Calendar.getInstance(TimeZone.getTimeZone("GMT")).timeInMillis)
    }

    private fun stringToDate(aDate: String): Date {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        return simpleDateFormat.parse(aDate) ?: Date()
    }
}