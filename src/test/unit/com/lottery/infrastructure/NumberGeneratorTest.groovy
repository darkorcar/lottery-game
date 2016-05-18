package com.lottery.infrastructure

import spock.lang.Specification

class NumberGeneratorTest extends Specification {

    def "should generate 6 numbers between 1 and 60"() {
        given:
        NumberGenerator numberGenerator = new NumberGenerator()

        when:
        List numbers = numberGenerator.generate()

        then:
        numbers.collect { 1 <= it && it <= 60 }.size() == 6
    }


    def "should generate valid numbers" () {
        given:
        NumberGenerator generator = new NumberGenerator()

        when:
        List listOfNumbers = (1..100000).collect{ generator.generate() }

        then:
        listOfNumbers.each { List<Integer> drawn ->
            assert drawn.size() == drawn.unique().size()
        }

    }


}
