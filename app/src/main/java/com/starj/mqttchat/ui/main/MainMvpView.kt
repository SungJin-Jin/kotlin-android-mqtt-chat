package com.starj.mqttchat.ui.main

import com.starj.mqttchat.common.BaseMvpView
import com.starj.mqttchat.datas.ChatRoom


interface MainMvpView : BaseMvpView {

    fun onLoadChatRooms(chatRooms: List<ChatRoom>)
}