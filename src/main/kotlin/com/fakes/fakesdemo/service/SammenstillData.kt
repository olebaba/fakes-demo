package com.fakes.fakesdemo.service

import com.fakes.fakesdemo.arbeidsforhold.Arbeidsforhold
import com.fakes.fakesdemo.arbeidsforhold.ArbeidsforholdClient
import com.fakes.fakesdemo.bruker.SykmeldingRepository
import org.springframework.stereotype.Service

@Service
class SammenstillData(
    private val arbeidsforholdClient: ArbeidsforholdClient,
    private val sykmeldingRepository: SykmeldingRepository,
) {
    fun sammenstillDataForSykmelding(sykmeldingId: String): SammenstilltData {
        val sykmelding =
            sykmeldingRepository.findById(sykmeldingId).orElse(null)
                ?: throw RuntimeException("Fant ikke sykmelding $sykmeldingId")

        return sammenstillDataForPerson(sykmelding.person)
    }

    private fun sammenstillDataForPerson(person: String): SammenstilltData {
        val arbeidsforhold =
            arbeidsforholdClient.hentArbeidsForholdForPerson(person)
                ?: throw RuntimeException("Fant ikke arbeidsforhold for person $person")

        return SammenstilltData(
            person = person,
            arbeidsforhold = arbeidsforhold,
        )
    }
}

data class SammenstilltData(
    val person: String,
    val arbeidsforhold: Arbeidsforhold,
)
