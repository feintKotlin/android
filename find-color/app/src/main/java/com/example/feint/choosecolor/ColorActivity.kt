package com.example.feint.choosecolor

import android.content.Intent
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.example.feint.choosecolor.factory.CellFactory
import com.example.feint.choosecolor.factory.ColorFactory
import com.example.feint.choosecolor.pojo.Level
import org.jetbrains.anko.*

class ColorActivity : AppCompatActivity() {
    val TAG = ColorActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_color)

        val levelIntent = intent;

        val level = Level(levelIntent.getIntExtra("level", 1))
        val context = this
        //colorPair.first:颜色不同的方格所在的位置；colorPair.second：颜色列表
        val colorPair = ColorFactory(level, context).getRGBList()

        //获取屏幕宽度
        val size = Point()
        windowManager.defaultDisplay.getRealSize(size)

        //整个页面的布局
        linearLayout {
            orientation = LinearLayout.VERTICAL
            for (j in 0..level.level) {

                //创建一行方格
                CellFactory.create(this, j, level + 1,
                        colorPair.second.slice((j) * (level + 1)..(j + 1) * (level + 1) - 1), size.x) { id ->

                    val intent = Intent(context, ColorActivity::class.java)
                    //当方格的id与差异色块的序号相同时，表示选择正确，进入下一个等级
                    if (id == colorPair.first) {
                        intent.putExtra("level", level + 1)

                        if (level + 1 == 13) {
                            toast("恭喜通关")
                            intent.putExtra("level", 1)
                        }
                    } else {
                        toast("挑战失败")
                        intent.putExtra("level", 1)
                    }

                    context.finish()
                    startActivity(intent)
                }
            }
            //显示当前的等级
            textView {
                textSize = 20F
                text = "等级：${level.level}"
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            }
            //显示当前得分
            textView {
                textSize = 16F
                text = "分数：${level.score}"
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            }
        }
    }
}
