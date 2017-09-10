package com.example.feint.feintkotlin.network

import com.example.feint.feintkotlin.pojo.Req
import com.example.feint.feintkotlin.pojo.Response
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectReader
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URL
import java.util.concurrent.TimeUnit

/**
 * Created by feint on 17/9/9.
 */
class NetworkUtil{
    val TAG=NetworkUtil::class.simpleName

    val httpClient=OkHttpClient.Builder().connectTimeout(15,TimeUnit.SECONDS).build()

    inline fun <reified T> requestList(url:URL):Response<List<T>>{
        val request=Request.Builder().url(url).build()

        val response=httpClient.newCall(request).execute()

        val mapper=ObjectMapper()

       // Log.i(TAG,response.body()!!.string())

        val jsonObj=JSONObject(response.body()!!.string())
        val data=mapper.readValue<List<T>>(jsonObj.getString("data"),
                mapper.typeFactory.constructParametricType(List::class.java,T::class.java)
                )
        val req=mapper.readValue<Req>(jsonObj.getString("req"),
                Req::class.java)

        return Response<List<T>>(data,req)

    }

    inline fun <reified T> request(url:URL):Response<T>{
        val request=Request.Builder().url(url).build()

        val response=httpClient.newCall(request).execute()

        val mapper=ObjectMapper()

        // Log.i(TAG,response.body()!!.string())

        val jsonObj=JSONObject(response.body()!!.string())
        val data=mapper.readValue<T>(jsonObj.getString("data"), T::class.java)
        val req=mapper.readValue<Req>(jsonObj.getString("req"),
                Req::class.java)

        return Response<T>(data,req)

    }
}

