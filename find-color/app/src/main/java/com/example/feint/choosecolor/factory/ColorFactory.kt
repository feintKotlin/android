package com.example.feint.choosecolor.factory

import android.content.Context
import android.graphics.Color
import com.example.feint.choosecolor.R
import com.example.feint.choosecolor.pojo.Level
import com.example.feint.choosecolor.pojo.RGB

/**
 * Created by feint on 17/9/7.
 */
class ColorFactory(val level: Level, val context:Context){
    val TAG= ColorFactory::class.simpleName
    val baseColorList:List<Int>

    init {
        baseColorList= listOf(context.getColor(R.color.red),context.getColor(R.color.darkBlue),
                context.getColor(R.color.lightBlue),context.getColor(R.color.lightGreen),
                context.getColor(R.color.yellow),context.getColor(R.color.black))
    }

    fun getRGBList():Pair<Int,List<RGB>>{
        val difIndex=(level.cellNum*Math.random()).toInt()
      //  Log.i(TAG,difIndex.toString())
        val color=Color().toRGB(baseColorList[difIndex%baseColorList.size])

        val rgbList= List<RGB>(level.cellNum){
            return@List if (it==difIndex)
                difColor(color,level.level)
            else
                color
        }

        return difIndex to rgbList
    }

    fun difColor(rgb: RGB, level: Int): RGB {
        val rgb_=rgb.copy()
        val offset=26-level*2
        rgb_.blue-=offset
        rgb_.green-=offset
        rgb_.red-=offset
        return rgb_
    }
}

fun Color.toRGB(color:Int): RGB = RGB(Color.red(color), Color.green(color), Color.blue(color))

