package com.starj.mqttchat.datas

import com.stfalcon.chatkit.commons.models.IUser

data class Author(
        private var id: String = "",
        private var name: String = "",
        private var avatar: String = ""
) : IUser {
    override fun getId() = id

    override fun getName() = name

    override fun getAvatar() = avatar
}