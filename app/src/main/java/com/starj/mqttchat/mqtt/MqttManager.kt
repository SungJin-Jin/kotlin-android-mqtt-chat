package com.starj.mqttchat.mqtt

import com.starj.mqttchat.common.EndPoint
import com.starj.mqttchat.datas.Author
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


class MqttManager(
        private val author: Author,
        private val topic: String,
        private val onSubscribed: (String, MqttMessage) -> Unit
) {

    companion object {
        private const val QOS_LEVEL = 2
        private const val INTERVAL_KEEP_ALIVE = 1000 * 60 * 60 * 1
    }

    private lateinit var client: MqttClient

    tailrec fun connect(): Boolean {
        client = MqttClient(EndPoint.ENDPOINT_MQTT_BROKER, author.id, MemoryPersistence())
        client.connect(
                MqttConnectOptions().apply {
                    keepAliveInterval = INTERVAL_KEEP_ALIVE
                    isCleanSession = false
                    isAutomaticReconnect = true
                }
        )

        return when (client.isConnected) {
            true -> {
                subscribeOnTopic()
                true
            }
            false -> connect()
        }
    }

    fun disconnect(): Unit = if (client.isConnected) client.disconnect() else Unit

    fun subscribeOnTopic(): Unit = client.subscribe(topic, QOS_LEVEL, onSubscribed::invoke)

    tailrec fun publish(input: String) {
        when (client.isConnected) {
            true -> client.publish(topic, input.toByteArray(), QOS_LEVEL, false)
            false -> if (connect()) publish(input)
        }
    }


}