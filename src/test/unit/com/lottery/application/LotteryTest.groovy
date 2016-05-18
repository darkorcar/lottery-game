package com.lottery.application

import com.lottery.application.draw.DrawGenerator
import com.lottery.application.draw.DrawSummariser
import com.lottery.domain.Bet
import com.lottery.domain.Draw
import com.lottery.util.date.DateProvider
import spock.lang.Specification

import static java.util.Calendar.FEBRUARY

class LotteryTest extends Specification {

    private DrawGenerator drawGeneratorMock = Mock();
    private DateProvider dateProviderMock = Mock();
    private DrawSummariser drawSummariserMock = Mock();
    private Writer writerMock = Mock();

    private Lottery lottery

    def setup() {
        dateProviderMock.currentDate() >> CURRENT_DATE()
        lottery = new Lottery(
                drawGeneratorMock,
                dateProviderMock,
                drawSummariserMock,
                writerMock
        )
    }

    def "should run lottery game for added bets"() {
        given:
        lottery.add(new Bet())
        drawGeneratorMock.generateDraws(*_) >> [new Draw()]

        when:
        lottery.run()

        then:
        1 * drawSummariserMock.summarise(*_)
        1 * writerMock.write(*_)
    }

    def CURRENT_DATE() {
        def date = new Date().clearTime()
        date.set(year: 2015, month: FEBRUARY, date: 20)
        date
    }
}
