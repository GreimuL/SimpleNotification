package com.greimul.simplenotification.bgservice

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import com.greimul.simplenotification.MainActivity
import com.greimul.simplenotification.R
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
                    statusPush()
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

    fun statusPush(){

        val intent = Intent(this,MainActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pending = PendingIntent.getActivity(this,0,intent,0)

        var builder =  NotificationCompat.Builder(this,"TestCH")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("New Notification!")
            .setContentText("New")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pending)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)){
            notify(0,builder.build())
        }
    }
}