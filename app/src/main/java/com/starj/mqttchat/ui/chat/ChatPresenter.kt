package com.starj.mqttchat.ui.chat

import com.starj.mqttchat.common.BaseMvpPresenter
import com.starj.mqttchat.common.BaseMvpView


class ChatPresenter<MvpView : BaseMvpView> : BaseMvpPresenter<MvpView> {

    private lateinit var view: ChatMvpView

    override fun attachView(view: MvpView) {
        this.view = view as ChatMvpView
    }

    override fun destroy() {}

}