package com.lottery

import org.junit.Ignore
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

// TODO should load test context since we don't want to bind integration test to System.in
@Ignore
@IntegrationTest
@ContextConfiguration(classes = LotteryApplication, loader = SpringApplicationContextLoader)
class LotteryApplicationTest extends Specification {

    def "should load application context"() {
        expect:
        true
    }

}