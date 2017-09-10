package com.example.feint.feintkotlin.frag

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.solver.widgets.ConstraintWidgetContainer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.feint.feintkotlin.DetailActivity
import com.example.feint.feintkotlin.R
import com.example.feint.feintkotlin.network.NetworkContract
import com.example.feint.feintkotlin.network.loadArticlePage
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

/**
 * Created by feint on 17/9/10.
 */
class TodayFragment:Fragment() {

    lateinit var tvTitle: TextView
    lateinit var tvIntro: TextView
    lateinit var tvTopic:TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_today,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initView()
    }

    fun initView(){
        tvTitle=find(R.id.tv_title)

        tvIntro=find(R.id.tv_intro)

        tvTopic=find(R.id.tv_topic)

        loadArticlePage({1}, {},{toast("failed")}){
            val article=it.data[0]

            tvTitle.text=article.title

            tvIntro.text=article.intro

            tvTopic.text=article.topic as String

            find<ConstraintLayout>(R.id.container).onClick {
                val intent=Intent(this@TodayFragment.context,DetailActivity::class.java)
                intent.putExtra(NetworkContract.ARTICLE_ID,article.id)
                startActivity(intent)
            }
        }


    }
}