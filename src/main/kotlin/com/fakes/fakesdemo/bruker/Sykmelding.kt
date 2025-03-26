package com.fakes.fakesdemo.bruker

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table
data class Sykmelding(
    @Id
    val id: String? = null,
    val periode: Periode,
    val person: String,
    val gyldig: Boolean,
)

data class Periode(
    val fom: LocalDate,
    val tom: LocalDate? = null,
)
