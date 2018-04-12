package com.starj.mqttchat.ui.chat

import com.starj.mqttchat.common.BaseMvpPresenter
import com.starj.mqttchat.common.BaseMvpView
import com.starj.mqttchat.datas.Author
import com.starj.mqttchat.datas.Message


class ChatPresenter<MvpView : BaseMvpView> : BaseMvpPresenter<MvpView> {

    private lateinit var view: ChatMvpView
    private lateinit var author: Author

    override fun attachView(view: MvpView) {
        this.view = view as ChatMvpView
    }

    override fun destroy() {}

    fun setAuthor(author: Author) {
        this.author = author
    }

    fun sendMessage(input: CharSequence?) {
        val message = Message(author = author, text = input.toString())
        view.onReceiveMessage(message)
    }

}