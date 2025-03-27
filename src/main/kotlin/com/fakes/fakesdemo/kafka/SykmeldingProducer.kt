package com.fakes.fakesdemo.kafka

import com.fakes.fakesdemo.sykmelding.Sykmelding
import com.fakes.fakesdemo.utils.serialisertTilString
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Component

interface SykmeldingProducer {
    fun produserSykmelding(sykmelding: Sykmelding): Boolean
}

@Component
class SykmeldingProducerKafka(
    private val meldingProducer: Producer<String, String>,
) : SykmeldingProducer {
    override fun produserSykmelding(sykmelding: Sykmelding): Boolean {
        try {
            meldingProducer
                .send(
                    ProducerRecord(
                        SYKMELDING_TOPIC,
                        sykmelding.id,
                        sykmelding.serialisertTilString(),
                    ),
                ).get()
            return true
        } catch (ex: Exception) {
            println("Klarte ikke sende sykmelding på kafka: ${sykmelding.id}")
            throw ex
        }
    }
}

const val SYKMELDING_TOPIC = "SYKMELDING_TOPIC"
