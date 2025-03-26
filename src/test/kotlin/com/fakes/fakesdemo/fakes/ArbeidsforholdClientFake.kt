package com.fakes.fakesdemo.fakes

import com.fakes.fakesdemo.arbeidsforhold.Arbeidsforhold
import com.fakes.fakesdemo.arbeidsforhold.ArbeidsforholdClient

class ArbeidsforholdClientFake : ArbeidsforholdClient {
    private val arbeidsforhold = mutableMapOf<String, Arbeidsforhold>()

    override fun hentArbeidsForholdForPerson(person: String): Arbeidsforhold? = arbeidsforhold[person]

    fun leggTilArbeidsforhold(
        person: String,
        arbeidsforhold: Arbeidsforhold,
    ) {
        this.arbeidsforhold[person] = arbeidsforhold
    }

    fun reset() {
        arbeidsforhold.clear()
    }
}
