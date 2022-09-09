package com.bakharaalief.graphqlappic.util

import android.annotation.SuppressLint
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*


object Helper {

    fun isAccessExpired(date: String): Boolean {
        var isExpired = false
        val expiredDate: Date = stringToDate(date, "EEE MMM d HH:mm:ss zz yyyy")
        if (Date().after(expiredDate)) isExpired = true
        return isExpired
    }

    @SuppressLint("SimpleDateFormat")
    private fun stringToDate(aDate: String, aFormat: String): Date {
        val pos = ParsePosition(0)
        val simpleDateFormat = SimpleDateFormat(aFormat)
        return simpleDateFormat.parse(aDate, pos) ?: Date()
    }
}