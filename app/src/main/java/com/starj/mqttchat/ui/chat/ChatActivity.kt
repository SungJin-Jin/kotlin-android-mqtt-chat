package com.starj.mqttchat.ui.chat

import android.os.Bundle
import android.view.View
import com.starj.mqttchat.R
import com.starj.mqttchat.common.BaseActivity
import com.starj.mqttchat.common.option.centerCropOptions
import com.starj.mqttchat.datas.Author
import com.starj.mqttchat.datas.Message
import com.starj.mqttchat.extensions.getAndroidId
import com.starj.mqttchat.extensions.load
import com.starj.mqttchat.extensions.showToast
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : BaseActivity(), ChatMvpView {

    private lateinit var presenter: ChatPresenter<ChatMvpView>
    private lateinit var messageAdapter: MessagesListAdapter<Message>

    private val userId by lazy { getAndroidId() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        initTitle()
        initView()
        bindEvent()
        connect()
    }

    private fun initTitle() {
        title = intent.extras.getString("title")
    }

    private fun initView() {
        messageAdapter = MessagesListAdapter(
                userId,
                ImageLoader { view, url -> view.load(url, centerCropOptions(R.drawable.ic_profile, R.drawable.ic_profile)) }
        )
        messageAdapter.registerViewClickListener(R.id.messageUserAvatar, this)

        rvMessage.setAdapter(messageAdapter)
    }

    private fun bindEvent() {
        inputMessage.setInputListener(this)
    }

    private fun connect() {
        loading.visibility = View.VISIBLE
        presenter.connect(Author(userId), title.toString())
    }

    override fun initPresenter() {
        presenter = ChatPresenter()
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun onSuccessConnect() {
        loading.visibility = View.GONE
    }

    override fun onErrorConnect() {
        showToast("Error to connect")
        finish()
    }

    override fun onSubmit(input: CharSequence?): Boolean {
        presenter.sendMessage(input)

        return true
    }

    override fun onReceiveMessage(message: Message) = messageAdapter.addToStart(message, true)

    override fun onMessageViewClick(view: View?, message: Message?) {}


}
