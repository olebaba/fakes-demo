package com.fakes.fakesdemo.service

import com.fakes.fakesdemo.tjeneste.TjenesteClient
import org.springframework.stereotype.Service

@Service
class SammenstillData(
    private val tjenesteClient: TjenesteClient,
) {
    fun sammenstillDataForBruker(brukerId: String): String {
        val brukerData = tjenesteClient.hentTjenesteMedId(brukerId)
        return "Data for bruker med id $brukerId: $brukerData"
    }
}
