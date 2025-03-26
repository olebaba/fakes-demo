package com.fakes.fakesdemo.kafka

import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfig(
    @Value("\${KAFKA_BROKERS}") private val kafkaBrokers: String,
) {
    @Bean
    fun kafkaProducer(): KafkaProducer<String, String> =
        KafkaProducer<String, String>(
            mapOf(
                CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG to kafkaBrokers,
                KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                ACKS_CONFIG to "all",
            ),
        )
}
