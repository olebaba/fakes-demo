package com.fakes.fakesdemo.fakes

import com.fakes.fakesdemo.tjeneste.TjenesteClient

class TjenesteClientFake : TjenesteClient {
    private val tjenesteVerdier = mutableMapOf<String, String>()

    override fun hentTjenesteMedId(id: String): String = tjenesteVerdier[id] ?: "Fant ingen tjeneste med id $id"

    fun leggTilTjeneste(
        id: String,
        tjeneste: String,
    ) {
        tjenesteVerdier[id] = tjeneste
    }

    fun reset() {
        tjenesteVerdier.clear()
    }
}
