package com.mj.appmvi.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//extension pls
fun Long.millisToDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat(Const.DATE_FORMAT, Locale.getDefault())
    return format.format(date)
}