package com.greimul.simplenotification.server

import android.util.Log
import com.greimul.simplenotification.MainActivity
import com.greimul.simplenotification.NotificationData
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.nio.Buffer

fun getNotification(id:Int,listener: GetListener):NotificationData?{
    val retrofit = Retrofit.Builder().baseUrl(homeURL).addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(RetrofitInterface::class.java)
    service.getNotification(id).enqueue(object: Callback<NotificationData> {
        override fun onResponse(call: Call<NotificationData>, response: Response<NotificationData>) {
            listener.serveNotification(response.body())
            Log.d("getNote","good")
        }
        override fun onFailure(call: Call<NotificationData>, t: Throwable) {
            Log.d("getNote","fail")
        }
    })
    return null
}
fun getAllNotification(listener:GetListener){
    val retrofit = Retrofit.Builder().baseUrl(homeURL).addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(RetrofitInterface::class.java)
    service.getAllNotification().enqueue(object:Callback<List<NotificationData>>{
        override fun onResponse(
            call: Call<List<NotificationData>>,
            response: Response<List<NotificationData>>
        ) {
            listener.serveAllNotification(response.body())
            Log.d("getAll","good")
        }
        override fun onFailure(call: Call<List<NotificationData>>, t: Throwable) {
            Log.d("getAll","fail")
        }
    })
}