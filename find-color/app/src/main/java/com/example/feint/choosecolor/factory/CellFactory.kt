package com.example.feint.choosecolor.factory

import android.graphics.Color
import android.view.ViewManager
import android.widget.LinearLayout
import com.example.feint.choosecolor.pojo.RGB
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.margin
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.textView

/**
 * Created by feint on 17/9/6.
 */
object CellFactory {
    val TAG= CellFactory::class.simpleName
    /**
     * line: 当前方格所在的行数，从0开始计数
     * cols：一共有几列方格
     * windowWidth：屏幕的宽度
     * listener：处理方格被点击之后发生的事情
     */
    fun create(view: ViewManager,
               line:Int,
               cols:Int,
               color: List<RGB>,
               windowWidth: Int,
               listener:(id:Int)->Unit): LinearLayout {
        val marginValue = 4
        return view.linearLayout {
            orientation = LinearLayout.HORIZONTAL
            for (i in 0..cols-1) {
                val id=(line)*cols+i

                textView {
                    backgroundColor = Color.rgb(color[i].red,color[i].green,color[i].blue)
                    textView@this.id=id

                }.lparams {
                    margin = marginValue
                    weight = 1F
                    height = (windowWidth - 8 * marginValue) / cols
                }.onClick {
                    listener(id)
                }
            }
        }
    }

}


