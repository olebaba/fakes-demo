package com.fakes.fakesdemo.api

import com.fakes.fakesdemo.service.SammenstillData
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class StandardCrudController(
    private val sammenstillData: SammenstillData,
) {
    @GetMapping("/api/v1/bruker-med-data/{brukerId}")
    @ResponseBody
    fun getBrukerMedData(
        @PathVariable brukerId: String,
    ): String = sammenstillData.sammenstillDataForBruker(brukerId)
}
