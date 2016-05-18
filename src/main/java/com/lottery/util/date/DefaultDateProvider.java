package com.lottery.util.date;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DefaultDateProvider implements DateProvider {

    @Override
    public Date currentDate() {
        return new Date();
    }

}
