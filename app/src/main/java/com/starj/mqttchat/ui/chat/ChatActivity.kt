package com.starj.mqttchat.ui.chat

import android.os.Bundle
import com.starj.mqttchat.R
import com.starj.mqttchat.common.BaseActivity

class ChatActivity : BaseActivity(), ChatMvpView {

    private lateinit var presenter: ChatPresenter<ChatMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        title = intent.extras.getString("title")
    }

    override fun initPresenter() {
        presenter = ChatPresenter()
        presenter.attachView(this)
    }
}
