package com.starj.mqttchat.ui.main

import com.starj.mqttchat.common.BaseMvpPresenter
import com.starj.mqttchat.common.BaseMvpView
import com.starj.mqttchat.datas.ChatRoom


class MainPresenter<MvpView : BaseMvpView> : BaseMvpPresenter<MvpView> {

    companion object {
        val defaultChatRooms = listOf(
                ChatRoom("1", "Nethru DEV 1"),
                ChatRoom("2", "Nethru DEV 2"),
                ChatRoom("3", "TEDda")
        )
    }

    private lateinit var view: MainMvpView

    override fun attachView(view: MvpView) {
        this.view = view as MainMvpView
    }

    override fun destroy() {}

    fun loadChatRooms() {
        // TODO : SharedPreferences 로 채팅방 리스트 관리 로직 추가
        view.onLoadChatRooms(defaultChatRooms)
    }

}