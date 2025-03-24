package com.fakes.fakesdemo.fakes

import com.fakes.fakesdemo.bruker.Bruker
import com.fakes.fakesdemo.bruker.BrukerRepository

class BrukerRepositoryFake :
    AbstractCrudRepositoryFake<Bruker>(
        getEntityId = { it.id },
        setEntityId = { entity, id -> entity.copy(id = id) },
    ),
    BrukerRepository {
    override fun getAllByAlder(alder: Int): List<Bruker> = entities.values.filter { it.alder == alder }
}
