package com.example.feint.feintkotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import br.tiagohm.markdownview.MarkdownView
import com.example.feint.feintkotlin.network.NetworkContract
import br.tiagohm.markdownview.css.styles.Github
import com.example.feint.feintkotlin.network.loadArticleDetail
import com.example.feint.feintkotlin.utility.DateUtil
import com.example.feint.feintkotlin.view.mdView
import com.example.feint.feintkotlin.view.pb
import org.jetbrains.anko.*


class DetailActivity : AppCompatActivity() {

    lateinit var pbLoadding:ProgressBar

    lateinit var mdvArticle:MarkdownView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_detail)

        frameLayout {
            pbLoadding=pb()

            mdvArticle=mdView {
                val css = Github()
                setEscapeHtml(false)

                css.addRule("body", "line-height: 1.6", "padding: 8px")

                css.addRule("p,li,strong,a,span","word-break:break-all")

                //css.addRule("li","white-space:break-all")

                addStyleSheet(css)
            }
        }

        loadArticleDetail({
            pbLoadding.visibility= View.VISIBLE
            intent.getLongExtra(NetworkContract.ARTICLE_ID,0)
        },{

        },{
            toast(it)
            pbLoadding.visibility=View.INVISIBLE
        }){
           val header="""
            |## ${it.title}
            |
            |${it.topic}   ${DateUtil.dateFormat(it.subdate)}
            |
            |----""".trimMargin()

            mdvArticle.loadMarkdown("$header\n${it.content}")
            pbLoadding.visibility=View.INVISIBLE
        }
    }
}
