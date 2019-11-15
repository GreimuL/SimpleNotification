package com.greimul.simplenotification

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.notification_item.view.*

class NotificationViewAdapter(private val notificationList:MutableList<NotificationData>):
    RecyclerView.Adapter<NotificationViewAdapter.NotificationViewHolder>(){
    inner class NotificationViewHolder(val notificationView: View):RecyclerView.ViewHolder(notificationView){
        init{
            itemView.setOnClickListener(object :View.OnClickListener{
                override fun onClick(v: View) {
                    clickListner.onClick(v,adapterPosition)
                }
            })
        }
    }
    lateinit var clickListner:OnClickListener
    interface OnClickListener{
        fun onClick(v:View,pos:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val notificationView = LayoutInflater.from(parent.context).inflate(R.layout.notification_item,parent,false)
        return NotificationViewHolder(notificationView)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.notificationView.dateText.text = notificationList[position].date
        holder.notificationView.titleView.text = notificationList[position].title
        holder.notificationView.userView.text = notificationList[position].user
    }

    override fun getItemCount() = notificationList.size
}