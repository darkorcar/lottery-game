package com.camelot.lottery.application

import com.camelot.lottery.domain.Bet
import spock.lang.Specification

class GameControllerTest extends Specification {

    private Reader readerMock = Mock()
    private Lottery lotteryMock = Mock()

    private GameController gameController

    def setup() {
        gameController = new GameController(
                readerMock,
                lotteryMock
        )
    }

    def "should read inputs and run the lottery game"() {
        given:
        readerMock.hasNext() >>> [true, false]
        readerMock.next() >> { new Bet() }

        when:
        gameController.start()

        then:
        1 * readerMock.next()
        1 * lotteryMock.run();
    }

    def "should swallow invalid input exception and continue reading the inputs"() {
        given:
        readerMock.hasNext() >>> [true, true, false]
        readerMock.next() >> [{ throw new InvalidInputException() }, { new Bet() }]

        when:
        gameController.start()

        then:
        2 * readerMock.next()
        noExceptionThrown()
    }
}
