package com.fakes.fakesdemo.service

import com.fakes.fakesdemo.FellesTestOppsett
import com.fakes.fakesdemo.fakes.TjenesteClientFake
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SammenstillDataTest : FellesTestOppsett() {
    @Autowired
    lateinit var tjenesteClient: TjenesteClientFake

    @Autowired
    lateinit var sammenstillData: SammenstillData

    @Test
    fun `skal hente tjeneste med id`() {
        tjenesteClient.leggTilTjeneste("1", "Tjeneste 1")

        val data = sammenstillData.sammenstillDataForBruker("1")
        data `should be equal to` "Data for bruker med id 1: Tjeneste 1"
    }
}
