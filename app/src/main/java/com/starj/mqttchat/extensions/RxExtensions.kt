package com.starj.mqttchat.extensions

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Single<T>.onMain() = observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

