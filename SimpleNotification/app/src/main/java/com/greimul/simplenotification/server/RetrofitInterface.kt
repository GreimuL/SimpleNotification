package com.greimul.simplenotification.server

import com.greimul.simplenotification.NotificationData
import com.greimul.simplenotification.idData
import okhttp3.ResponseBody
import okio.Utf8
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("insert.php")
    fun postNotification(@Query("title") title:String,
                         @Query("user") user:String,
                         @Query("description") description:String
    ): Call<idData>

    @POST("give_all_notification.php")
    fun getAllNotification():Call<List<NotificationData>>

    @GET("delete.php")
    fun deleteNotification(@Query("id") id:Int):Call<ResponseBody>

    @GET("give_notification.php")
    fun getNotification(@Query("id") id:Int):Call<NotificationData>
}