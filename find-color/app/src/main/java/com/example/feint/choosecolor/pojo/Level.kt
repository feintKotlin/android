package com.example.feint.choosecolor.pojo

/**
 * Created by feint on 17/9/7.
 */
class Level(val level: Int){

    val cellNum
            get()=Math.pow((level+1).toDouble(),2.0).toInt()

    val score
            get() = (level-1)*100

    operator fun plus(num:Int)=this.level+num
}