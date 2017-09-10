package com.example.feint.feintkotlin.utility

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by feint on 17/9/9.
 */
object DateUtil{
    inline fun dateFormat(timestamp: Timestamp)=SimpleDateFormat("yyyy-MM-dd").format(Date(timestamp.time))
}