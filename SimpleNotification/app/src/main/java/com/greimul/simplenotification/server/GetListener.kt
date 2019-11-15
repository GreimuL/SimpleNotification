package com.greimul.simplenotification.server

import com.greimul.simplenotification.NotificationData

interface GetListener {
    fun serveAllNotification(data:List<NotificationData>?)
    fun serveNotification(data:NotificationData?)
    fun getInsertID(id:Int)
    fun onFail()
}