package com.starj.mqttchat.datas

import com.stfalcon.chatkit.commons.models.IMessage
import org.joda.time.DateTime
import java.util.*

data class Message(
        private var id: String = "",
        private var createdAt: Long = DateTime.now().millis,
        private var author: Author,
        private var text: String = ""
) : IMessage {
    override fun getId() = id

    override fun getCreatedAt() = Date(createdAt)

    override fun getUser() = author

    override fun getText() = text
}
