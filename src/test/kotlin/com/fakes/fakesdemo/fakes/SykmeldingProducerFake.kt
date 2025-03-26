package com.fakes.fakesdemo.fakes

import com.fakes.fakesdemo.bruker.Sykmelding
import com.fakes.fakesdemo.kafka.SykmeldingProducer

class SykmeldingProducerFake : SykmeldingProducer {
    private val sendteSykmeldinger = mutableMapOf<String, Sykmelding>()

    override fun produserSykmelding(sykmelding: Sykmelding): Boolean {
        sendteSykmeldinger[sykmelding.id!!] = sykmelding
        return true
    }

    fun sendteSykmeldinger(): Map<String, Sykmelding> = sendteSykmeldinger

    fun reset() {
        sendteSykmeldinger.clear()
    }
}
