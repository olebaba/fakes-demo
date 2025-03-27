package com.fakes.fakesdemo.sykmelding

import com.fakes.fakesdemo.IntegrasjonTestOppsett
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be null`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class SykmeldingRepositoryTest : IntegrasjonTestOppsett() {
    @Autowired
    lateinit var sykmeldingRepository: SykmeldingRepository

    @Test
    fun `skal lagre og hente sykmelding`() {
        val sykmelding =
            sykmeldingRepository.save(
                Sykmelding(
                    status = "NY",
                    fom = LocalDate.parse("2020-01-01"),
                    tom = LocalDate.parse("2020-01-20"),
                    person = "person",
                ),
            )
        sykmelding.id.`should not be null`()

        val hentetSykmelding = sykmeldingRepository.findById(sykmelding.id!!).get()
        hentetSykmelding.`should not be null`()
        hentetSykmelding.person `should be equal to` "person"
    }
}
