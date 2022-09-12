package com.bakharaalief.graphqlappic.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


object Helper {

    fun isAccessExpired(date: String): Boolean {
        val expiredDate: Date = stringToDate(date, "yyyy-MM-dd'T'HH:mm:ss")
        return Date().after(expiredDate)
    }

    fun stringToDate(aDate: String, aFormat: String): Date {
        val simpleDateFormat = SimpleDateFormat(aFormat, Locale.getDefault())
        return simpleDateFormat.parse(aDate) ?: Date()
    }
}