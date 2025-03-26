package com.fakes.fakesdemo.arbeidsforhold

import com.fakes.fakesdemo.IntegrasjonTestOppsett
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should not be null`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ArbeidsforholdClientEksternTest : IntegrasjonTestOppsett() {
    @Autowired
    lateinit var arbeidsforholdClient: ArbeidsforholdClient

    @Test
    fun `hentArbeidsforhold skal returnere arbeidsforhold`() {
        val arbeidsforhold = arbeidsforholdClient.hentArbeidsForholdForPerson("person")
        arbeidsforhold.`should not be null`()
        arbeidsforhold.person `should be equal to` "person"
    }
}
