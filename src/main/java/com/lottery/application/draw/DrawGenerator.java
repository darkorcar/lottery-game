package com.lottery.application.draw;

import com.lottery.domain.Draw;
import com.lottery.infrastructure.NumberGenerator;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DrawGenerator {

    private static int NUMBERS_OF_MONTHS = 6;

    private NumberGenerator numberGenerator = new NumberGenerator();

    public List<Draw> generateDraws(Date startDate) {
        List<Draw> draws = new ArrayList();

        List<Date> mondays = getNextMondays(startDate);

        for (Date monday : mondays) {
            Draw draw = new Draw()
                    .setDate(monday)
                    .setNumbers(generateNumbers());
            draws.add(draw);
        }

        return draws;
    }

    private List<Integer> generateNumbers() {
        return numberGenerator.generate();
    }

    private List<Date> getNextMondays(Date startDate) {
        Date endDate = DateUtils.addMonths(startDate, NUMBERS_OF_MONTHS);
        Calendar iteratorDate = Calendar.getInstance();
        iteratorDate.setTime(startDate);
        List<Date> mondays = new ArrayList();

        while (iteratorDate.getTime().before(endDate)) {
            if (iteratorDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                mondays.add(iteratorDate.getTime());
            }
            iteratorDate.add(Calendar.DATE, 1);
        }

        return mondays;
    }

    public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }
}
