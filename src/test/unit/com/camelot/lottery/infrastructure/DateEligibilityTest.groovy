package com.camelot.lottery.infrastructure

import com.camelot.lottery.application.InvalidInputException
import com.camelot.lottery.util.date.DateProvider
import org.apache.commons.lang3.time.DateUtils
import spock.lang.Specification

import static java.util.Calendar.JANUARY

class DateEligibilityTest extends Specification {

    def "should throw an exception if date not at least 6 months a head"() {
        given:
        DateProvider dateProviderStub = Stub() { currentDate() >> CURRENT_DATE }
        Date date = CURRENT_DATE_PLUS_3_MONTHS

        when:
        new DateEligibility(dateProviderStub).verify(date)

        then:
        thrown(InvalidInputException)
    }

    def "should not throw an exception if date is at least 6 months a head"() {
        given:
        DateProvider dateProviderStub = Stub() { currentDate() >> CURRENT_DATE }
        Date date = CURRENT_DATE_PLUS_6_MONTHS

        when:
        new DateEligibility(dateProviderStub).verify(date)

        then:
        noExceptionThrown()
    }

    def CURRENT_DATE = new Date(
            year: 2015,
            month: JANUARY,
            date: 10
    )

    def CURRENT_DATE_PLUS_3_MONTHS = DateUtils.addMonths(CURRENT_DATE, 3)
    def CURRENT_DATE_PLUS_6_MONTHS = DateUtils.addMonths(CURRENT_DATE, 6)
}
