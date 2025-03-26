package com.fakes.fakesdemo.service

import com.fakes.fakesdemo.arbeidsforhold.ArbeidsforholdClient
import com.fakes.fakesdemo.bruker.Sykmelding
import com.fakes.fakesdemo.bruker.SykmeldingRepository
import com.fakes.fakesdemo.kafka.SykmeldingProducer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class SykmeldingHandterer(
    private val arbeidsforholdClient: ArbeidsforholdClient,
    private val sykmeldingRepository: SykmeldingRepository,
    private val sykmeldingProducer: SykmeldingProducer,
) {
    @Transactional
    fun sendSykmelding(sykmeldingId: String) {
        val sykmelding = sykmeldingRepository.findById(sykmeldingId).get()
        validerSykmelding(sykmelding)
        sykmeldingRepository.save(sykmelding.copy(status = "SENDT"))
        sykmeldingProducer.produserSykmelding(sykmelding = sykmelding)
    }

    private fun validerSykmelding(sykmelding: Sykmelding) {
        val person = sykmelding.person
        val arbeidsforhold =
            arbeidsforholdClient.hentArbeidsForholdForPerson(person)
                ?: throw RuntimeException("Fant ikke arbeidsforhold for person $person")

        val arbeidsforholdFom = arbeidsforhold.periode.fom
        val arbeidsforholdTom = arbeidsforhold.periode.tom

        if (!erSykmeldingForArbeidsforhold(sykmelding, arbeidsforholdTom, arbeidsforholdFom)) {
            throw RuntimeException("Sykmelding er ikke for arbeidsforholdet")
        }
    }

    private fun erSykmeldingForArbeidsforhold(
        sykmelding: Sykmelding,
        arbeidsforholdTom: LocalDate?,
        arbeidsforholdFom: LocalDate,
    ) = if (arbeidsforholdTom != null) {
        sykmelding.periode.fom in arbeidsforholdFom..arbeidsforholdTom
    } else {
        sykmelding.periode.fom >= arbeidsforholdFom
    }
}
