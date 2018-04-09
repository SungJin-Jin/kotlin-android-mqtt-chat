package com.starj.mqttchat.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.starj.mqttchat.R
import com.starj.mqttchat.common.BaseActivity
import com.starj.mqttchat.datas.ChatRoom
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainMvpView {

    private lateinit var presenter: MainPresenter<MainMvpView>
    private lateinit var chatRoomAdapter: ChatRoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initLayout()
    }

    override fun initPresenter() {
        presenter = MainPresenter()
        presenter.attachView(this)
    }

    override fun onLoadChatRooms(chatRooms: List<ChatRoom>) = chatRoomAdapter.addItems(chatRooms)

    private fun initLayout() {
        chatRoomAdapter = ChatRoomAdapter { }

        rvChatRooms.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvChatRooms.adapter = chatRoomAdapter

        presenter.loadChatRooms()
    }
}
