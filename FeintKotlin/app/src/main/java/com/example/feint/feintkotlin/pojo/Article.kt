package com.example.feint.feintkotlin.pojo

import java.sql.Timestamp

/**
 * Created by feint on 17/9/9.
 */
data class Article(val id:Long=0,
                   var title:String="",
                   var content:String="",
                   var uid:Long=0,
                   var subdate:Timestamp= Timestamp(System.currentTimeMillis()),
                   var type:Any?=null,
                   var topic:Any?=null,
                   var tag:String="",
                   var chk:Byte=0,
                   var intro:String="")