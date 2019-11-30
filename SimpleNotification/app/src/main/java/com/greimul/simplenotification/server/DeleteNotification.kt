package com.greimul.simplenotification.server

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun deleteNotification(id:Int){
    val retrofit = Retrofit.Builder().baseUrl(HOME_URL).addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(RetrofitInterface::class.java)
    service.deleteNotification(id).enqueue(object: Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            Log.d("Test","$response")
        }
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Log.d("Test2","Bad")
        }
    })
}