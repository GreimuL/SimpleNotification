package com.greimul.simplenotification

import com.google.gson.annotations.SerializedName

data class NotificationData(val id:Int,val date:String,val title:String,val user:String,val description:String)
data class idData(val id:Int)
//null data
val nullData = NotificationData(0,"","","","")