package com.starj.mqttchat.ui.chat

import com.starj.mqttchat.common.BaseMvpView
import com.starj.mqttchat.datas.Message
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesListAdapter

interface ChatMvpView :
        BaseMvpView,
        MessageInput.InputListener,
        MessagesListAdapter.OnMessageViewClickListener<Message> {

    fun onReceiveMessage(message: Message)

    fun onSuccessConnect()

    fun onErrorConnect()

}
