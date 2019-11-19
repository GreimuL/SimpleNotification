package com.greimul.simplenotification

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class NotificationData(val id:Int,val date:String,val title:String,val user:String,val description:String)
data class idData(val id:Int)
//null data
val nullData = NotificationData(0,"","","","")