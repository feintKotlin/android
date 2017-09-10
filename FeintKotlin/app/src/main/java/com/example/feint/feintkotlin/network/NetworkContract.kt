package com.example.feint.feintkotlin.network

import android.net.Uri
import java.net.URI
import java.net.URL

/**
 * Created by feint on 17/9/9.
 */
object NetworkContract{
    val ARTICLE_ID="aid"

    val PROTOCOL_HTTP="http"

    val ARTICLE_HOST="39.108.124.125"

    val ARTICLE_PORT=8688

    /**
     * Article API
     */

    val ARTICLE_TOP=RestfulAPI("article/top")

    val ARTICLE_DETAIL=RestfulAPI("article/detail/full")


}

class RestfulAPI(val path:String){
    fun fullPath(vararg args:String):String{
        var argPath=""
        args.forEach { argPath+="/$it" }

        return path+argPath
    }
}
