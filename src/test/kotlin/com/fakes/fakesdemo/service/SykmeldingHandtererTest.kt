package com.fakes.fakesdemo.service

import com.fakes.fakesdemo.FakesTestOppsett
import com.fakes.fakesdemo.arbeidsforhold.Arbeidsforhold
import com.fakes.fakesdemo.fakes.ArbeidsforholdClientFake
import com.fakes.fakesdemo.fakes.SykmeldingRepositoryFake
import com.fakes.fakesdemo.sykmelding.Sykmelding
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be null`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class SykmeldingHandtererTest : FakesTestOppsett() {
    @Autowired
    lateinit var arbeidsforholdClient: ArbeidsforholdClientFake

    @Autowired
    lateinit var sykmeldingRepository: SykmeldingRepositoryFake

    @Autowired
    lateinit var sykmeldingHandterer: SykmeldingHandterer

    @Test
    fun `skal sende sykmelding`() {
        arbeidsforholdClient.leggTilArbeidsforhold(
            arbeidsforhold =
                Arbeidsforhold(
                    person = "person",
                    arbeidsgiver = "Jobben AS",
                    stilling = "Arbeider",
                    fom = LocalDate.parse("2020-01-01"),
                ),
        )

        sykmeldingRepository.save(
            Sykmelding(
                id = "1",
                fom = LocalDate.parse("2025-01-01"),
                tom = LocalDate.parse("2025-01-20"),
                person = "person",
                status = "NY",
            ),
        )

        sykmeldingHandterer.sendSykmelding("1")
        val sendtSykmelding = sykmeldingRepository.findById("1").get()
        sendtSykmelding.`should not be null`()
        sendtSykmelding.status `should be equal to` "SENDT"
    }
}
