package com.greimul.simplenotification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greimul.simplenotification.server.GetListener
import com.greimul.simplenotification.server.getAllNotification
import com.greimul.simplenotification.server.getNotification
import com.greimul.simplenotification.server.postNotification
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_notification.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView:RecyclerView
    private lateinit var viewManager:RecyclerView.LayoutManager
    private lateinit var viewAdapter:NotificationViewAdapter
    private var notificationArray:MutableList<NotificationData> = mutableListOf()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null) {
            val deletePosition = data.getIntExtra("pos", -1)
            if (deletePosition != -1) {
                deleteItem(deletePosition)
                Log.d("isdel", "$deletePosition")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val deletePosition = intent.getIntExtra("pos",-1)
        viewManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
        viewAdapter = NotificationViewAdapter(notificationArray)
        recyclerView = notificationRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        viewAdapter.clickListner = object:NotificationViewAdapter.OnClickListener{
            override fun onClick(v: View, pos: Int) {
                startActivityForResult(Intent(this@MainActivity,DescriptionActivity::class.java)
                    .putExtra("id",notificationArray[pos].id)
                    .putExtra("pos",pos),0)
                Log.d("Click","${notificationArray[pos]}")
            }
        }
        getAllData()
        addNotificationButton.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_notification,null)
            dialog.setView(dialogView).setPositiveButton("Push!"){
                    dialog,i->
                postData(
                    dialogView.addTitleEdit.text.toString(),
                    dialogView.addUserEdit.text.toString(),
                    dialogView.addDescriptionEdit.text.toString()
                )
            }.setNegativeButton("Cancle"){
                    dialog,i->
            }.show()
        }
        refreshButton.setOnClickListener {
            getAllData()
        }
    }
    private fun deleteItem(id:Int){
        notificationArray.removeAt(id)
        viewAdapter.notifyItemRemoved(id)
    }
    private  fun insertItem(data:NotificationData){
        notificationArray.add(data)
        viewAdapter.notifyItemInserted(notificationArray.size-1)
        recyclerView.scrollToPosition(notificationArray.size-1)
    }
    private fun postData(title:String,user:String,description:String):Int{
        var insertID = -1
        postNotification(title,user,description,object:GetListener{
            override fun getInsertID(id:Int) {
                insertID =id
                getData(insertID)
            }
            override fun serveAllNotification(data: List<NotificationData>?) {}
            override fun serveNotification(data: NotificationData?) {}
            override fun onFail() {}
        })
        return insertID
    }
    private fun getData(id:Int):NotificationData{
        var returnData = nullData
       getNotification(id,object:GetListener{
           override fun serveNotification(data: NotificationData?) {
               returnData = data?: nullData
               insertItem(returnData)
           }
           override fun serveAllNotification(data: List<NotificationData>?) {}
           override fun getInsertID(id:Int) {}
           override fun onFail() {}
       })
        return returnData
    }
    private fun getAllData(){
        getAllNotification(object:GetListener{
            override fun serveAllNotification(data: List<NotificationData>?) {
                notificationArray.clear()
                if(data!=null) {
                    notificationArray.addAll(data)
                    viewAdapter.notifyDataSetChanged()
                }
                Log.d("Array","$notificationArray")
            }
            override fun serveNotification(data: NotificationData?) {}
            override fun getInsertID(id:Int) {}
            override fun onFail() {}
        })
    }
}
