package com.starj.mqttchat.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.starj.mqttchat.R
import com.starj.mqttchat.datas.ChatRoom
import kotlinx.android.synthetic.main.viewholder_chat_room.view.*
import java.util.*
import kotlin.math.abs

internal class ChatRoomAdapter(val action: (chatRoom: ChatRoom) -> Unit) : RecyclerView.Adapter<ChatRoomAdapter.ViewHolder>() {

    private val chatRooms = ArrayList<ChatRoom>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.draw(chatRooms[position])

    override fun getItemCount() = chatRooms.size

    fun addItems(items: List<ChatRoom>) {
        chatRooms.clear()
        chatRooms.addAll(items)
        notifyDataSetChanged()
    }

    fun update(chatRoom: ChatRoom) {
        var position = -1
        chatRooms.indices.forEach {
            if (chatRoom.id == chatRooms[it].id) {
                chatRooms[it].name = chatRoom.name
                position = it
                return
            }
        }
        notifyItemChanged(position)
    }

    fun addItem(chatRoom: ChatRoom) {
        chatRooms.add(chatRoom)
        notifyItemInserted(chatRooms.size - 1)
    }

    fun clear() {
        chatRooms.clear()
        notifyDataSetChanged()
    }

    internal inner class ViewHolder(parent: ViewGroup)
        : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_chat_room, parent, false)) {

        fun draw(chatRoom: ChatRoom) {
            with(itemView) {
                name.text = chatRoom.name

                photo.setTextAndColor(
                        chatRoom.name.first().toUpperCase().toString(),
                        AvatarImageView.COLORS[abs(chatRoom.hashCode()) % AvatarImageView.COLORS.size]
                )

                setOnClickListener { action.invoke(chatRoom) }
            }
        }
    }
}