package com.fakes.fakesdemo.bruker

import org.springframework.data.repository.CrudRepository

interface BrukerRepository : CrudRepository<Bruker, String> {
    fun getAllByAlder(alder: Int): List<Bruker>
}
