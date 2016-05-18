package com.camelot.lottery.application.draw

import com.camelot.lottery.domain.Bet
import com.camelot.lottery.domain.Draw
import com.camelot.lottery.domain.DrawSummary
import spock.lang.Specification

class DrawSummariserTest extends Specification {

    private DrawSummariser drawSummariser = new DrawSummariser()

    def "should calculate £210,000 winning for Straight Bingo"() {
        given:
        List bets = [
                new Bet(
                        numbers: [1, 2, 3, 4, 5, 6]
                )
        ]

        when:
        DrawSummary drawSummary = drawSummariser.summarise(straightDraw, bets)

        then:
        drawSummary.betSummaries[0].winning == 210000.00
    }

    def "If they guessed all six numbers they will win the sum of £10,000 multiplied by every number drawn"() {
        given:
        List bets = [
                new Bet(
                        numbers: [1, 2, 3, 4, 5, 7]
                )
        ]

        when:
        DrawSummary drawSummary = drawSummariser.summarise(draw, bets)

        then:
        drawSummary.betSummaries[0].winning == 220000.00
    }

    def """If they guessed three or more numbers
           they will win a £1000 pounds
           for every number they guessed plus the multiplication of the numbers they missed."""() {
        given:
        List bets = [
                new Bet(
                        numbers: [1, 2, 3, 4, 8, 9]
                )
        ]

        when:
        DrawSummary drawSummary = drawSummariser.summarise(draw, bets)

        then:
        drawSummary.betSummaries[0].winning == 40072.00
    }

    def "If the player guessed less than 3 numbers in the draw, they will win the sum of the numbers drawn"() {
        given:
        List bets = [
                new Bet(
                        numbers: [1, 2, 8, 9, 10, 11]
                )
        ]

        when:
        DrawSummary drawSummary = drawSummariser.summarise(draw, bets)

        then:
        drawSummary.betSummaries[0].winning == draw.numbers.sum()
    }

    // TODO
    def "During the month of February on a leap year, all winnings are doubled"() {

    }

    private Draw straightDraw = new Draw(
            date: new Date(),
            numbers: [1, 2, 3, 4, 5, 6]
    )

    private Draw draw = new Draw(
            date: new Date(),
            numbers: [1, 2, 3, 4, 5, 7]
    )
}
