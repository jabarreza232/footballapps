package com.evolutions.jabar.footballclub.view

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


object DateTimeConverter {
    fun longDate(date:String):String{
        return toGMTFormat(date,"HH:mm")
    }

    fun timeDate(time:String):String{
        return toGMTFormat(time,"EEE,dd MMMM yyyy")

    }

    @SuppressLint("SimpleDateFormat")
    fun toGMTFormat(date:String, time:String) : String {
            var convert=""
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val parse = dateFormat.parse(date)
            val timeFormat = SimpleDateFormat(time)
            timeFormat.timeZone = TimeZone.getTimeZone("Asia/Bangkok")
            convert = timeFormat.format(parse)

            return convert


        }


}
/*
@SuppressLint("SimpleDateFormat")
fun dateFormat(date:Date?):String? = with(date?:Date()){
    SimpleDateFormat("EEE,dd MMMM yyyy").format(this)
}

@SuppressLint("SimpleDateFormat")
fun toGMTFormat(date:String?,time:String?):Date?{
    val format = SimpleDateFormat("yyy/MM/dd HH:mm:ss")
    format.timeZone= TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return format.parse(dateTime)
*/
