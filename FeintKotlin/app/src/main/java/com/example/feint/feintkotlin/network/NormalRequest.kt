package com.example.feint.feintkotlin.network

import com.example.feint.feintkotlin.pojo.Article
import com.example.feint.feintkotlin.pojo.Response
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import java.net.URL

/**
 * Created by feint on 17/9/10.
 */
fun loadArticlePage(start: () -> Int, success: () -> Unit, failed: (msg:String) -> Unit,doing:(Response<List<Article>>)->Unit){
    async(UI) {
        val page=start()
        try {
            val result = bg {
                val url= URL("${NetworkContract.PROTOCOL_HTTP}://${NetworkContract.ARTICLE_HOST}:" +
                        "${NetworkContract.ARTICLE_PORT}" +
                        "/${NetworkContract.ARTICLE_TOP.fullPath(page.toString())}")
                val articleList = NetworkUtil().requestList<Article>(url)
                return@bg articleList
            }
            val resopnse= result.await();
            if(resopnse.req.code==200) {
                doing(resopnse)
                success()
            }else
                failed(resopnse.req.msg)

        } catch (e: Exception) {
            failed("Program Exception")
        }


    }
}

fun loadArticleDetail(start: () -> Long, success: () -> Unit, failed: (msg:String) -> Unit,doing:(Article)->Unit){
    async(UI) {
        val aid=start()
        try {
            val result = bg {
                val url= URL("${NetworkContract.PROTOCOL_HTTP}://${NetworkContract.ARTICLE_HOST}:" +
                        "${NetworkContract.ARTICLE_PORT}" +
                        "/${NetworkContract.ARTICLE_DETAIL.fullPath(aid.toString())}")

                val article = NetworkUtil().request<Article>(url)
                return@bg article
            }

            val resopnse= result.await();
            if(resopnse.req.code==200) {
                doing(resopnse.data)
                success()
            }else
                failed(resopnse.req.msg)

        } catch (e: Exception) {
            failed("Program Exception")
        }


    }
}