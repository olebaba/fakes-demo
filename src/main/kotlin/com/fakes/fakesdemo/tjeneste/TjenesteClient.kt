package com.fakes.fakesdemo.tjeneste

import org.springframework.stereotype.Component

interface TjenesteClient {
    fun hentTjenesteMedId(id: String): String
}

@Component
class TjenesteClientEkstern : TjenesteClient {
    override fun hentTjenesteMedId(id: String): String {
        Thread.sleep(1000)
        return "Hentet tjeneste med id $id"
    }
}
