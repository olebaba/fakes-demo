package com.fakes.fakesdemo.bruker

import org.springframework.data.repository.CrudRepository

interface SykmeldingRepository : CrudRepository<Sykmelding, String> {
    fun getAllByPerson(person: String): List<Sykmelding>

    fun getAllByGyldig(gyldig: Boolean): List<Sykmelding>
}
