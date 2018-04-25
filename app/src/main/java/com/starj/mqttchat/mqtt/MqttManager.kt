package com.starj.mqttchat.mqtt

import com.starj.mqttchat.common.EndPoint
import com.starj.mqttchat.datas.Author
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MqttManager(
        val author: Author,
        val topic: String,
        val actionOnSubscribed: (String, MqttMessage) -> Unit
) {
    companion object {
        private const val QOS_LEVEL = 2
    }

    private lateinit var mqttClient: MqttClient

    tailrec fun connect() {
        mqttClient = MqttClient(EndPoint.ENDPOINT_MQTT_BROKER, author.id, MemoryPersistence())
        mqttClient.connect(
                MqttConnectOptions().also {
                    it.isCleanSession = true
                    it.isAutomaticReconnect = true
                    it.userName = "lzgnfwsn"
                    it.password = "t5I3zShOEwyP".toCharArray()
                }
        )

        return if(mqttClient.isConnected) {
            subcribeOnTopic()
        } else {
            connect()
        }
    }

    private fun subcribeOnTopic() {
        mqttClient.subscribe(
                topic, QOS_LEVEL, actionOnSubscribed
        )
    }

    fun disconnect() {
        if(mqttClient.isConnected) mqttClient.disconnect()
    }

    fun publish(message: String) {
        mqttClient.publish(topic, message.toByteArray(), QOS_LEVEL, false)
    }

}
