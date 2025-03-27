package com.fakes.fakesdemo.sykmelding

import org.springframework.data.repository.CrudRepository

interface SykmeldingRepository : CrudRepository<Sykmelding, String> {
    fun getAllByPerson(person: String): List<Sykmelding>

    fun getAllByStatus(status: String): List<Sykmelding>
}
