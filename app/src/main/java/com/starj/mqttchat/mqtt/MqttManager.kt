package com.starj.mqttchat.mqtt

import com.starj.mqttchat.common.EndPoint
import com.starj.mqttchat.datas.Author
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MqttManager(
        val author: Author,
        private val topic: String,
        private val actionOnSubscribed: (String, MqttMessage) -> Unit
) {
    companion object {
        private const val QOS_LEVEL = 2

        // TODO : Set account of yours
        private const val ACCOUNT_USER_NAME = "lzgnfwsn"
        private const val ACCOUNT_PASSWORD = "t5I3zShOEwyP"
    }

    private val mqttClient: MqttClient by lazy {
        MqttClient(EndPoint.ENDPOINT_MQTT_BROKER, author.id, MemoryPersistence())
    }

    private val connectOptions: MqttConnectOptions by lazy {
        MqttConnectOptions().apply {
            isCleanSession = true
            isAutomaticReconnect = true
            userName = ACCOUNT_USER_NAME
            password = ACCOUNT_PASSWORD.toCharArray()
        }
    }

    tailrec fun connect(): Boolean {
        return if (mqttClient.isConnected) {
            subscribeOnTopic()

            true
        } else {
            mqttClient.connect(connectOptions)

            connect()
        }
    }

    fun disconnect() = if (mqttClient.isConnected) mqttClient.disconnect() else Unit

    fun publish(message: String) = mqttClient.publish(topic, message.toByteArray(), QOS_LEVEL, false)

    private fun subscribeOnTopic() = mqttClient.subscribe(topic, QOS_LEVEL, actionOnSubscribed)
}
