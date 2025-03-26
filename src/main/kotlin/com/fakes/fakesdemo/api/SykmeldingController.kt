package com.fakes.fakesdemo.api

import com.fakes.fakesdemo.service.SammenstillData
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class SykmeldingController(
    private val sammenstillData: SammenstillData,
) {
    @PostMapping("/api/v1/sykmelding/send/{sykmeldingId}")
    @ResponseBody
    fun sendSykmelding(
        @PathVariable sykmeldingId: String,
    ): ResponseEntity<String> {
        val arbeidsforhold = sammenstillData.sammenstillDataForSykmelding(sykmeldingId)

        return ResponseEntity.ok("Sykmelding sendt til ${arbeidsforhold.arbeidsforhold.arbeidsgiver}")
    }
}
