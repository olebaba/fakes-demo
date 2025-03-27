package com.fakes.fakesdemo.fakes

import com.fakes.fakesdemo.sykmelding.Sykmelding
import com.fakes.fakesdemo.sykmelding.SykmeldingRepository
import java.util.*

class SykmeldingRepositoryFake : SykmeldingRepository {
    private val sykmeldinger = mutableMapOf<String, Sykmelding>()

    override fun getAllByPerson(person: String): List<Sykmelding> = sykmeldinger.values.filter { it.person == person }

    override fun getAllByStatus(status: String): List<Sykmelding> = sykmeldinger.values.filter { it.status == status }

    override fun <S : Sykmelding?> save(entity: S & Any): S & Any {
        sykmeldinger[entity.id!!] = entity
        return entity
    }

    override fun <S : Sykmelding?> saveAll(entities: MutableIterable<S>): MutableIterable<S> {
        entities.forEach { entity -> sykmeldinger[entity?.id!!] = entity }
        return entities
    }

    override fun findById(id: String): Optional<Sykmelding> = Optional.ofNullable(sykmeldinger[id])

    override fun existsById(id: String): Boolean = sykmeldinger.containsKey(id)

    override fun findAll(): MutableIterable<Sykmelding> = sykmeldinger.values

    override fun findAllById(ids: MutableIterable<String>): MutableIterable<Sykmelding> =
        ids.mapNotNull { id -> sykmeldinger[id] }.toMutableList()

    override fun count(): Long = sykmeldinger.size.toLong()

    override fun deleteById(id: String) {
        sykmeldinger.remove(id)
    }

    override fun delete(entity: Sykmelding) {
        sykmeldinger.remove(entity.id)
    }

    override fun deleteAllById(ids: MutableIterable<String>) {
        ids.forEach { id -> sykmeldinger.remove(id) }
    }

    override fun deleteAll(entities: MutableIterable<Sykmelding>) {
        entities.forEach { entity -> sykmeldinger.remove(entity.id) }
    }

    override fun deleteAll() {
        sykmeldinger.clear()
    }
}
