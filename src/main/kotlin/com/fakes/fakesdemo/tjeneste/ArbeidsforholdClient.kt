package com.fakes.fakesdemo.tjeneste

import com.fakes.fakesdemo.bruker.Periode
import org.springframework.stereotype.Component
import java.time.LocalDate

interface ArbeidsforholdClient {
    fun hentArbeidsForholdForPerson(person: String): Arbeidsforhold?
}

@Component
class ArbeidsforholdClientEkstern : ArbeidsforholdClient {
    override fun hentArbeidsForholdForPerson(person: String): Arbeidsforhold {
        Thread.sleep(1000)
        return Arbeidsforhold(
            arbeidsgiver = "Jobben AS",
            stilling = "Arbeider",
            periode =
                Periode(
                    fom = LocalDate.parse("2020-01-01"),
                ),
        )
    }
}

data class Arbeidsforhold(
    val arbeidsgiver: String,
    val stilling: String,
    val periode: Periode,
)
