package com.greimul.simplenotification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.notification_item.view.*

class NotificationViewAdapter(private val notificationList:MutableList<NotificationData>):
    RecyclerView.Adapter<NotificationViewAdapter.NotificationViewHolder>(){
    class NotificationViewHolder(val notificationView: View):RecyclerView.ViewHolder(notificationView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val notificationView = LayoutInflater.from(parent.context).inflate(R.layout.notification_item,parent,false)
        return NotificationViewHolder(notificationView)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.notificationView.dateView.text = notificationList[position].date
        holder.notificationView.titleView.text = notificationList[position].title
        holder.notificationView.descriptionView.text = notificationList[position].description
        holder.notificationView.userView.text = notificationList[position].user
    }

    override fun getItemCount() = notificationList.size
}