package com.camelot.lottery.application.draw

import com.camelot.lottery.infrastructure.NumberGenerator
import spock.lang.Specification

import static java.util.Calendar.JANUARY

class DrawGeneratorTest extends Specification {

    private NumberGenerator numberGeneratorMock = Mock() { generate() >> [1, 2, 3, 4, 5, 6] }

    def "should generate draws for every monday next 6 months from given date"() {
        given:
        DrawGenerator drawGenerator = new DrawGenerator(
                numberGenerator: numberGeneratorMock
        )

        when:
        List draws = drawGenerator.generateDraws(CURRENT_DATE)

        then:
        draws.size() == 26
    }

    def CURRENT_DATE = new Date(
            year: 2015,
            month: JANUARY,
            date: 10
    )
}
