package com.lottery.infrastructure

import com.lottery.domain.Bet
import spock.lang.Specification

import static java.util.Calendar.FEBRUARY

class ConsoleReaderTest extends Specification {

    def "should read a bet from console input"() {
        given:
        String input = "20/02/2015 1 2 3 4 5 6"
        InputStream inputStream = new ByteArrayInputStream(input.bytes)

        ConsoleReader consoleReader = new ConsoleReader(inputStream)
        consoleReader.betBuilder = betBuilder

        Bet expectedBet = new Bet(
                date: FEBRUARY_20(),
                numbers: [1, 2, 3, 4, 5, 6]
        )

        when:
        consoleReader.hasNext()

        then:
        consoleReader.next() == expectedBet
    }

    BetBuilder betBuilder = new BetBuilder(
            dateEligibility: Mock(DateEligibility)
    )

    def FEBRUARY_20() {
        def date = new Date().clearTime()
        date.set(year: 2015, month: FEBRUARY, date: 20)
        date
    }
}
