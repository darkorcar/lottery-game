package com.camelot.lottery.infrastructure;

import com.camelot.lottery.application.InvalidInputException;
import com.camelot.lottery.util.date.DateProvider;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public class DateEligibility {

    private DateProvider dateProvider;

    public DateEligibility(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    public void verify(Date date) {
        Date dateMinus6Months = DateUtils.addMonths(date, -6);
        Date currentDate = dateProvider.currentDate();

        if (currentDate.after(dateMinus6Months))
            throw new InvalidInputException("Date must be at least 6 months from now.");
    }

}
