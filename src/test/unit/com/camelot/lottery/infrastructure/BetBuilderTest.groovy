package com.camelot.lottery.infrastructure

import com.camelot.lottery.application.InvalidInputException
import com.camelot.lottery.domain.Bet
import spock.lang.Specification

import static java.util.Calendar.FEBRUARY

class BetBuilderTest extends Specification {

    private DateEligibility dateEligibilityMock = Mock()
    BetBuilder betBuilder

    def setup() {
        betBuilder = new BetBuilder(
                dateEligibility: dateEligibilityMock
        )
    }

    def "should build Bet from parsed input"() {
        given:
        def input = ['20/02/2015', '1', '2', '3', '4', '5', '6'] as String[]
        dateEligibilityMock.verify(*_) >> {}

        Bet expectedBet = new Bet(
                date: FEBRUARY_20(),
                numbers: [1, 2, 3, 4, 5, 6]
        )

        when:
        Bet bet = betBuilder.build(input)

        then:
        bet == expectedBet
    }

    def "should throw an exception on invalid date format"() {
        given:
        def input = ['02/2015', '1', '2', '3', '4', '5', '6'] as String[]
        dateEligibilityMock.verify(*_) >> {}

        when:
        betBuilder.build(input)

        then:
        thrown(InvalidInputException)
    }

    def "should throw an exception on ineligible date"() {
        given:
        def input = ['20/02/2015', '1', '2', '3', '4', '5', '6'] as String[]
        dateEligibilityMock.verify(*_) >> { throw new InvalidInputException() }

        when:
        betBuilder.build(input)

        then:
        thrown(InvalidInputException)
    }

    def FEBRUARY_20() {
        def date = new Date().clearTime()
        date.set(year: 2015, month: FEBRUARY, date: 20)
        date
    }
}
