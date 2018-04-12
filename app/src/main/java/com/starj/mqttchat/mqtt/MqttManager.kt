package com.starj.mqttchat.mqtt

import com.starj.mqttchat.common.EndPoint.ENDPOINT_MQTT_BROKER
import com.starj.mqttchat.datas.Author
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


class MqttManager(
        var author: Author,
        private var topic: String,
        private var subscribeAction: (String, MqttMessage) -> Unit
) {

    companion object {
        private const val QOS_LEVEL = 2
        private const val INTERVAL_KEEP_ALIVE = 1000 * 60 * 60 * 24
    }

    private lateinit var client: MqttClient

    tailrec fun connect(): Boolean {
        client = MqttClient(ENDPOINT_MQTT_BROKER, author.id, MemoryPersistence())
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

    fun disconnect() {
        if (client.isConnected) client.disconnect()
    }

    private fun subscribeOnTopic() {
        client.subscribe(topic, QOS_LEVEL, subscribeAction::invoke)
    }

    tailrec fun publish(input: String) {
        when (client.isConnected) {
            true -> {
                client.publish(topic, input.toByteArray(), QOS_LEVEL, false)
            }
            false -> if (connect()) publish(input)
        }
    }
}