package com.greimul.simplenotification.server

import com.greimul.simplenotification.NotificationData
import com.greimul.simplenotification.idData
import okhttp3.ResponseBody
import okio.Utf8
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @FormUrlEncoded
    @POST("insert.php")
    fun postNotification(@FieldMap data:HashMap<String,String>): Call<idData>

    @GET("give_all_notification.php")
    fun getAllNotification():Call<List<NotificationData>>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteNotification(@Field("id") id:Int):Call<ResponseBody>

    @FormUrlEncoded
    @POST("give_notification.php")
    fun getNotification(@Field("id") id:Int):Call<NotificationData>

    @GET("give_last_notification.php")
    fun getLastNotification():Call<NotificationData>
}