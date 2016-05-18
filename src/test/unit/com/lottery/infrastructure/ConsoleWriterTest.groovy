package com.lottery.infrastructure

import com.lottery.domain.Bet
import com.lottery.domain.Draw
import com.lottery.domain.BetSummary
import com.lottery.domain.DrawSummary
import spock.lang.Specification

import static java.util.Calendar.FEBRUARY

class ConsoleWriterTest extends Specification {

    def "should write to output"() {
        given:
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        ConsoleWriter consoleWriter = new ConsoleWriter(printStream);

        DrawSummary drawSummary = new DrawSummary(
                draw: new Draw(
                        date: FEBRUARY_20(),
                        numbers: [1, 2, 3, 4, 5, 6]
                ),
                betSummaries: [
                        new BetSummary(
                                bet: new Bet(numbers: [1, 2, 3, 4, 5, 6]),
                                winning: 100.00
                        ),
                        new BetSummary(
                                bet: new Bet(numbers: [1, 2, 10, 11, 12, 13]),
                                winning: 10.00
                        )
                ]
        )

        when:
        consoleWriter.write(drawSummary)

        then:
        new String(outputStream.toByteArray()) == expectedOutput
    }

    private String getExpectedOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        printStream.println("Draw date: [20/02/2015] numbers: [1, 2, 3, 4, 5, 6]")
        printStream.println("Winnings:")
        printStream.println("[1, 2, 3, 4, 5, 6]                      prise:               100.0")
        printStream.println("[1, 2, 10, 11, 12, 13]                  prise:                10.0")
        printStream.println("------------------------------------------------------------------")

        return new String(outputStream.toByteArray());
    }

    def FEBRUARY_20() {
        def date = new Date()
        date.set(year: 2015, month: FEBRUARY, date: 20)
        date
    }
}
