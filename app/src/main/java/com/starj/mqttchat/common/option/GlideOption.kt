package com.starj.mqttchat.common.option

import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.starj.mqttchat.R


fun defaultOptions(placeHolder: Int = R.mipmap.ic_launcher, error: Int = R.mipmap.ic_launcher) =
        RequestOptions().placeholder(placeHolder).error(error)

fun centerCropOptions(placeHolder: Int = R.mipmap.ic_launcher, error: Int = R.mipmap.ic_launcher) =
        circleCropTransform().placeholder(placeHolder).error(error)

