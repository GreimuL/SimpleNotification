package com.greimul.simplenotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_notification.view.*
import kotlinx.android.synthetic.main.notification_item.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView:RecyclerView
    private lateinit var viewAdapter:RecyclerView.Adapter<*>
    private lateinit var viewManager:RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dummy:MutableList<NotificationData> = mutableListOf()
        for(i in 0..5){
            dummy.add(NotificationData("date","title+$i","$i","user+$i"))
        }
        viewManager = LinearLayoutManager(this).apply{
            reverseLayout = true
            stackFromEnd = true
        }
        viewAdapter = NotificationViewAdapter(dummy)
        recyclerView = notificationRecyclerView.apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        addNotificationButton.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_notification,null)
            dialog.setView(dialogView).setPositiveButton("Push!"){
                dialog,i->
                    dummy.add(NotificationData(Date().toString(),
                        dialogView.addTitleEdit.text.toString(),
                        dialogView.addDescriptionEdit.text.toString(),
                        dialogView.addUserEdit.text.toString()))
            }.setNegativeButton("Cancle"){
                dialog,i->
            }.show()
        }
    }
}
