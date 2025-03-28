package com.fakes.fakesdemo.arbeidsforhold

import org.springframework.stereotype.Component
import java.time.LocalDate

interface ArbeidsforholdClient {
    fun hentArbeidsForholdForPerson(person: String): Arbeidsforhold?
}

@Component
class ArbeidsforholdClientEkstern : ArbeidsforholdClient {
    override fun hentArbeidsForholdForPerson(person: String): Arbeidsforhold {
        // Kall til eksternt api

        return Arbeidsforhold(
            person = person,
            arbeidsgiver = "Jobben AS",
            stilling = "Arbeider",
            fom = LocalDate.parse("2020-01-01"),
        )
    }
}

data class Arbeidsforhold(
    val arbeidsgiver: String,
    val stilling: String,
    val person: String,
    val fom: LocalDate,
    val tom: LocalDate? = null,
)
