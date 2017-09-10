package com.example.feint.feintkotlin.frag

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.feint.feintkotlin.view.ArticleIntroRecyclerViewAdapter
import com.example.feint.feintkotlin.view.pb
import com.example.feint.feintkotlin.view.recycleView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onScrollChange


/**
 * Created by feint on 17/9/9.
 */
class IndexFragment : Fragment() {
    val TAG = IndexFragment::class.simpleName

    lateinit var mainView: FrameLayout
    lateinit var pbLoadding: ProgressBar
  //  lateinit var swLArticle: SwipeRefreshLayout
    lateinit var rvArticle: RecyclerView
    lateinit var articleAd: ArticleIntroRecyclerViewAdapter
    var articlePage = 1

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        initView()

        return mainView
        //return super.onCreateView(inflater, container, savedInstanceState)

    }

    fun initView() {

        articleAd = ArticleIntroRecyclerViewAdapter(context = this@IndexFragment.context)
        mainView = context.frameLayout {
            pbLoadding = pb()


            frameLayout {
                rvArticle = recycleView {
                    layoutManager = LinearLayoutManager(this@IndexFragment.context, LinearLayout.VERTICAL, false)
                    adapter = articleAd
                    itemAnimator = DefaultItemAnimator()
                    articleAd.loadData({
                        pbLoadding.visibility = View.VISIBLE
                        return@loadData articlePage
                    }, {
                        pbLoadding.visibility = View.INVISIBLE
                    }) {
                        pbLoadding.visibility = View.INVISIBLE
                        toast(it)
                    }
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }


            }


        }
        setListener()
    }


    fun setListener() {
        var previouItem = 0
        var loadding = true
        var loadLock = false
        val rvLayoutManager = rvArticle.layoutManager as LinearLayoutManager
        rvArticle.onScrollChange { _, _, _, _, _ ->
            //可见元素的数量
            var visibleItemCount = rvArticle.childCount
            //元素的总数量
            var totalItemCount = rvLayoutManager.itemCount
            //第一个可见元素的位置
            var firstVisiIndex = rvLayoutManager.findFirstVisibleItemPosition()

//            Log.i(TAG, "totalItemCount: ${totalItemCount}")
//            Log.i(TAG, "visibleItemCount: ${visibleItemCount}")
//            Log.i(TAG, "firstVisiIndex: ${firstVisiIndex}")

            if (loadding) {
                if (totalItemCount > previouItem) {
                    loadding = false
                    previouItem = totalItemCount
                }
            }
            //未处于加载状态，
            if (!loadLock && !loadding && (totalItemCount - visibleItemCount <= firstVisiIndex)) {
                articleAd.loadData({
                    loadLock = true
                    articlePage++
                    pbLoadding.visibility = View.VISIBLE
                    return@loadData articlePage
                }, {
                    pbLoadding.visibility = View.INVISIBLE
                    loadding = true
                    loadLock = false
                }) {
                    pbLoadding.visibility = View.INVISIBLE
                    toast(it)
                    loadLock = true
                }
            }

        }



//        swLArticle.onRefresh {
//
//        }

    }
}