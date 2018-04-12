package com.starj.mqttchat.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.widget.Toast



@SuppressLint("HardwareIds")
fun Context.getAndroidId() = android.provider.Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

fun Context.showToast(message: String) = Toast.makeText(this,  message, Toast.LENGTH_LONG).show()