package com.fakes.fakesdemo.bruker

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Bruker(
    @Id
    val id: String? = null,
    val navn: String,
    val alder: Int,
    val syk: Boolean,
)
