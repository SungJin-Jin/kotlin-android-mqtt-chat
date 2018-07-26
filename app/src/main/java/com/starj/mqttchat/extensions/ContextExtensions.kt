package com.starj.mqttchat.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings.Secure.ANDROID_ID
import android.provider.Settings.Secure.getString
import android.widget.Toast
import java.util.*


@SuppressLint("HardwareIds")
fun Context.getAndroidId() = getString(this.contentResolver, ANDROID_ID) ?: UUID.randomUUID().toString()

fun Context.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
