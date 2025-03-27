package com.fakes.fakesdemo.sykmelding

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table
data class Sykmelding(
    @Id
    val id: String? = null,
    val status: String = "NY",
    val person: String,
    val fom: LocalDate,
    val tom: LocalDate,
)
