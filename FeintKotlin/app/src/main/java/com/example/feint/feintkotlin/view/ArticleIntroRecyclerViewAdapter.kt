package com.example.feint.feintkotlin.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.TextView
import com.example.feint.feintkotlin.DetailActivity
import com.example.feint.feintkotlin.R
import com.example.feint.feintkotlin.network.NetworkContract
import com.example.feint.feintkotlin.network.NetworkUtil
import com.example.feint.feintkotlin.network.loadArticlePage
import com.example.feint.feintkotlin.pojo.Article
import com.example.feint.feintkotlin.utility.DateUtil
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.*
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.net.URL

/**
 * Created by feint on 17/9/9.
 */
class ArticleIntroRecyclerViewAdapter(val data: MutableList<Article> = ArrayList(), val context: Context) : RecyclerView.Adapter<ArticleIntroViewHolder>() {
    val TAG = ArticleIntroRecyclerViewAdapter::class.simpleName

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleIntroViewHolder {
        //  Log.i(TAG,"create view holder")
        val inflate = LayoutInflater.from(context)
        val view = inflate.inflate(R.layout.rect_article_intro_item, parent, false)
        val lp = view.layoutParams as ViewGroup.MarginLayoutParams
        when (viewType) {
            1 -> lp.topMargin = context.dip(16)
            2 -> lp.bottomMargin = context.dip(16)
        }
        return ArticleIntroViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ArticleIntroViewHolder?, position: Int) {
        //    Log.i(TAG,data[position].toString())
        holder!!.bindView(data[position])

        holder.itemView.onClick {
            val intent=Intent(this@ArticleIntroRecyclerViewAdapter.context,DetailActivity::class.java)
            intent.putExtra(NetworkContract.ARTICLE_ID,data[position].id)
            this@ArticleIntroRecyclerViewAdapter.context.startActivity(intent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> 1
            data.size - 1 -> 2
            else -> 3
        }
    }

    fun loadData(start: () -> Int, success: () -> Unit, failed: (msg:String) -> Unit) {
        loadArticlePage(start,success,failed){
            data.addAll(data.size,it.data!!)
            notifyDataSetChanged()
        }
    }

}


class ArticleIntroViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle: TextView
    val tvDate:TextView
    val tvTopic: TextView

    init {
        tvTitle = view.find(R.id.tv_title)
        tvDate=view.find(R.id.tv_date)
        tvTopic = view.find(R.id.tv_topic)
    }

    fun bindView(article: Article) {
        tvTitle.text = article.title
        tvDate.text= DateUtil.dateFormat(article.subdate)
        tvTopic.text = article.topic as String
    }
}

