package com.fakes.fakesdemo.service

import com.fakes.fakesdemo.IntegrasjonTestOppsett
import com.fakes.fakesdemo.arbeidsforhold.Arbeidsforhold
import com.fakes.fakesdemo.arbeidsforhold.ArbeidsforholdClient
import com.fakes.fakesdemo.sykmelding.Sykmelding
import com.fakes.fakesdemo.sykmelding.SykmeldingRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be null`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class SykmeldingHandtererIntegrasjonTest : IntegrasjonTestOppsett() {
    @Autowired
    lateinit var sykmeldingRepository: SykmeldingRepository

    @Autowired
    lateinit var sykmeldingHandterer: SykmeldingHandterer

    @Test
    fun `skal sende sykmelding`() {
        mock<ArbeidsforholdClient> {
            on { hentArbeidsForholdForPerson("person") } doReturn
                Arbeidsforhold(
                    person = "person",
                    arbeidsgiver = "Jobben AS",
                    stilling = "Arbeider",
                    fom = LocalDate.parse("2020-01-01"),
                )
        }

        val sykmelding =
            sykmeldingRepository.save(
                Sykmelding(
                    fom = LocalDate.parse("2025-01-01"),
                    tom = LocalDate.parse("2025-01-20"),
                    person = "person",
                    status = "NY",
                ),
            )

        sykmeldingHandterer.sendSykmelding(sykmeldingId = sykmelding.id!!)
        val sendtSykmelding = sykmeldingRepository.findById(sykmelding.id!!).get()
        sendtSykmelding.`should not be null`()
        sendtSykmelding.status `should be equal to` "SENDT"
    }
}
