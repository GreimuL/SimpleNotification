package com.greimul.simplenotification

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.greimul.simplenotification.server.GetListener
import com.greimul.simplenotification.server.deleteNotification
import com.greimul.simplenotification.server.getNotification
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DescriptionActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        val id = intent.getIntExtra("id",0)
        val pos = intent.getIntExtra("pos",-1)
        getData(id)
        deleteButton.setOnClickListener {
            deleteNotification(id)
            setResult(Activity.RESULT_OK, Intent().putExtra("pos",pos))
            finish()
        }
    }
    private fun getData(id:Int){
        getNotification(id,object:GetListener{
            override fun serveNotification(data: NotificationData?) {
                var showData=data?: nullData
                dateView.text = showData.date
                titleView.text = showData.title
                userView.text = showData.user
                descriptionView.text = showData.description
            }
            override fun serveAllNotification(data: List<NotificationData>?) {}
            override fun getInsertID(id:Int) {}
            override fun onFail() {}
        })
    }
}