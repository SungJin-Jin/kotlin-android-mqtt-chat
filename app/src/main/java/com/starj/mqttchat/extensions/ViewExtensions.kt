package com.starj.mqttchat.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.starj.mqttchat.common.option.defaultOptions

fun ImageView.load(
        url: String,
        options: RequestOptions = defaultOptions()
) = Glide.with(context).load(url).apply(options).into(this)