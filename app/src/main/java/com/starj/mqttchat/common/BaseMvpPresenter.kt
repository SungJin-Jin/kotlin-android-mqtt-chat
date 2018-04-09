package com.starj.mqttchat.common

interface BaseMvpPresenter<T : BaseMvpView> {

    fun attachView(view: T)

    fun destroy()
}