package com.greimul.simplenotification.server

import android.util.Log
import com.greimul.simplenotification.MainActivity
import com.greimul.simplenotification.idData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import okio.Utf8
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun postNotification(title:String,user:String,description:String,listener:GetListener){
    val retrofit = Retrofit.Builder().baseUrl(HOME_URL).addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(RetrofitInterface::class.java)

    val data = HashMap<String,String>()
    data["title"] = title
    data["user"] = user
    data["description"] =description

    service.postNotification(data).enqueue(object: Callback<idData> {
        override fun onResponse(call: Call<idData>, response: Response<idData>) {
            val idData = response.body()?:idData(0)
            listener.getInsertID(idData.id)
            Log.d("Test","${response.body()}")
        }
        override fun onFailure(call: Call<idData>, t: Throwable) {
            Log.d("Test2","Bad")
        }
    })
}