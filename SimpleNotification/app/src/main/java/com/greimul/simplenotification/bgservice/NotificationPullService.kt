package com.greimul.simplenotification.bgservice

import android.app.IntentService
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.app.NotificationCompat
import com.greimul.simplenotification.notificationArray

class NotificationPullService: Service() {

    private var serviceLooper:Looper? = null
    private var serviceHandler:ServiceHandler?=null

    private inner class ServiceHandler(looper:Looper): Handler(looper){
        override fun handleMessage(msg: Message) {
            while(true) {
                Thread.sleep(5000)
                updateNewData()
                if (newData != null&& previousData!= newData) {
                    Log.d("New?","NewMSG!")
                    previousData = newData
                    newData = null
                }
                Log.d("background", "GOOD")
            }
        }
    }

    override fun onCreate() {
        HandlerThread("ServiceStartArguments",Process.THREAD_PRIORITY_BACKGROUND).apply{
            previousData = notificationArray.lastOrNull()
            start()
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        serviceHandler?.obtainMessage()?.also {msg->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
    }
}