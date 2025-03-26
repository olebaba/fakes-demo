package com.fakes.fakesdemo.api

import com.fakes.fakesdemo.service.SykmeldingHandterer
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class SykmeldingController(
    private val sykmeldingHandterer: SykmeldingHandterer,
) {
    @PostMapping("/api/v1/sykmelding/send/{sykmeldingId}")
    @ResponseBody
    fun sendSykmelding(
        @PathVariable sykmeldingId: String,
    ): ResponseEntity<String> {
        sykmeldingHandterer.sendSykmelding(sykmeldingId)

        return ResponseEntity.ok("Sykmelding med id $sykmeldingId ble sendt")
    }
}
