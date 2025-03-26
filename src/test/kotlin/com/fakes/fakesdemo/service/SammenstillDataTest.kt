package com.fakes.fakesdemo.service

import com.fakes.fakesdemo.FellesTestOppsett
import com.fakes.fakesdemo.arbeidsforhold.Arbeidsforhold
import com.fakes.fakesdemo.bruker.Periode
import com.fakes.fakesdemo.bruker.Sykmelding
import com.fakes.fakesdemo.fakes.ArbeidsforholdClientFake
import com.fakes.fakesdemo.fakes.SykmeldingRepositoryFake
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class SammenstillDataTest : FellesTestOppsett() {
    @Autowired
    lateinit var arbeidsforholdClient: ArbeidsforholdClientFake

    @Autowired
    lateinit var sykmeldingRepository: SykmeldingRepositoryFake

    @Autowired
    lateinit var sammenstillData: SammenstillData

    @Test
    fun `skal sende sykmelding`() {
        arbeidsforholdClient.leggTilArbeidsforhold(
            person = "person",
            arbeidsforhold =
                Arbeidsforhold(
                    arbeidsgiver = "Jobben AS",
                    stilling = "Arbeider",
                    periode = Periode(fom = LocalDate.parse("2020-01-01")),
                ),
        )

        sykmeldingRepository.save(
            Sykmelding(
                id = "1",
                periode = Periode(fom = LocalDate.parse("2025-01-01"), tom = LocalDate.parse("2025-01-20")),
                person = "person",
                gyldig = true,
            ),
        )

        val sammenstilltData = sammenstillData.sammenstillDataForSykmelding("1")
        sammenstilltData `should be equal to`
            SammenstilltData(
                person = "person",
                arbeidsforhold =
                    Arbeidsforhold(
                        arbeidsgiver = "Jobben AS",
                        stilling = "Arbeider",
                        periode = Periode(fom = LocalDate.parse("2020-01-01")),
                    ),
            )
    }
}
