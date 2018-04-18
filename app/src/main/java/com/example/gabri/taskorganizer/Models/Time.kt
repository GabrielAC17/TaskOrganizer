package com.example.gabri.taskorganizer.Models

class Time {
    var minute:Int = 0
    var hour:Int = 0

    constructor(hour:Int, minute:Int){
        this.hour = hour
        this.minute = minute
    }

    fun toStringHHMM():String{
        return hour.toString() + ":" + minute.toString()
    }

    fun toStringFull():String{
        return hour.toString() + "h" + minute.toString() + "min"
    }

    fun isEmpty():Boolean{
        return hour == 0 && minute == 0
    }

    operator fun minus(value:Time):Time{
        var timeResult = Time(0,0)
        timeResult.minutesToTime(timeToMinutes() - value.timeToMinutes())

        return timeResult
    }

    operator fun plus(value:Time):Time{
        var timeResult = Time(0,0)
        timeResult.minutesToTime(timeToMinutes() + value.timeToMinutes())

        return timeResult
    }

    operator fun unaryMinus() = Time(-hour, minute)

    fun minutesToTime(minutes:Int){
        hour = minutes / 60
        minute = minutes % 60
    }

    fun timeToMinutes():Int {
        return hour * 60 + minute
    }

    companion object {
        fun minutesToTimeString(minutes:Int):String{
            var hour = minutes / 60
            var minute = minutes % 60

            return hour.toString() + "h" + minute.toString() + "min"
        }
    }

}