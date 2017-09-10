package com.example.feint.feintkotlin.utility

import com.example.feint.feintkotlin.pojo.Article

/**
 * Created by feint on 17/9/9.
 */
object DataUtil{
    fun createArticleList()= listOf<Article>(
            Article(title = "",intro = ""),
            Article(title = "",intro = ""),
            Article(title = "",intro = ""),
            Article(title = "",intro = "")
    )
}