package com.greimul.simplenotification.bgservice

import android.util.Log
import com.greimul.simplenotification.NotificationData
import com.greimul.simplenotification.server.GetListener
import com.greimul.simplenotification.server.getAllNotification
import com.greimul.simplenotification.server.getLastNotification

var newData:NotificationData? = null
var previousData:NotificationData? = null

fun updateNewData(){
   getLastNotification(object:GetListener{
        override fun serveLastNotification(data: NotificationData?) {
            if(data!=null){
                newData = data
            }
        }
        override fun getInsertID(id: Int) {}
        override fun onFail() {}
        override fun serveAllNotification(data: List<NotificationData>?) {}
        override fun serveNotification(data: NotificationData?) {}
    })
}

