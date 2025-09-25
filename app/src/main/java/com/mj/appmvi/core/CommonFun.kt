package com.mj.appmvi.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun millisToDate(millis: Long): String {
    val date = Date(millis)
    val format = SimpleDateFormat(Const.DATE_FORMAT, Locale.getDefault())
    return format.format(date)
}