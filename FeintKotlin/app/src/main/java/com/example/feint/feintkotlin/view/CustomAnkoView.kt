package com.example.feint.feintkotlin.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.ProgressBar
import br.tiagohm.markdownview.MarkdownView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

/**
 * Created by feint on 17/9/10.
 */
inline fun _FrameLayout.pb():ProgressBar{
    return progressBar {
        elevation = dip(5).toFloat()
    }.lparams {
        width = dip(40)
        height = dip(40)
        gravity = Gravity.CENTER
    }
}

inline fun ViewManager.recycleView(init: (@AnkoViewDslMarker RecyclerView).() -> Unit): RecyclerView {
    return ankoView({ ctx: Context -> RecyclerView(ctx) }, 0) { init() }
}

inline fun ViewManager.mdView(init:(@AnkoViewDslMarker MarkdownView).()->Unit):MarkdownView{
    return ankoView({ctx: Context -> MarkdownView(ctx)  },0){init()}
}