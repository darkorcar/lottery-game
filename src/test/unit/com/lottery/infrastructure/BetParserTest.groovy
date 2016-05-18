package com.lottery.infrastructure

import com.lottery.application.InvalidInputException
import spock.lang.Specification

class BetParserTest extends Specification {

    def "should parse input string into array"() {
        given:
        String input = "20/02/2015 1 2 3 4 5 6"

        when:
        def parsedInput = new BetParser().parse(input)

        then:
        parsedInput[0] == '20/02/2015'
        parsedInput[1] == '1'
        parsedInput[2] == '2'
        parsedInput[3] == '3'
        parsedInput[4] == '4'
        parsedInput[5] == '5'
        parsedInput[6] == '6'
    }

    def "should throw an exception on invalid date"() {
        given:
        String input = "20/022015 1 2 3 4 5 6"

        when:
        new BetParser().parse(input)

        then:
        thrown(InvalidInputException)
    }

    def "should throw an exception on invalid number"() {
        given:
        String input = "20/022015 100 2 3 4 5 6"

        when:
        new BetParser().parse(input)

        then:
        thrown(InvalidInputException)
    }

    def "should throw an exception on invalid number elements"() {
        given:
        String input = "20/022015 1 2 3 4 5"

        when:
        new BetParser().parse(input)

        then:
        thrown(InvalidInputException)
    }
}
