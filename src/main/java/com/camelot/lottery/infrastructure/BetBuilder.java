package com.camelot.lottery.infrastructure;

import com.camelot.lottery.application.InvalidInputException;
import com.camelot.lottery.domain.Bet;
import com.camelot.lottery.util.date.DefaultDateProvider;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BetBuilder {

    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private DateEligibility dateEligibility;

    public BetBuilder() {
        this.dateEligibility = new DateEligibility(new DefaultDateProvider());
    }

    public Bet build(String[] parsedBet) {
        Date date = getDate(parsedBet[0]);

        List<Integer> numbers = getNumbers(parsedBet);

        validateDate(date);

        return new Bet()
                .setDate(date)
                .setNumbers(numbers);
    }

    private Date getDate(String date) {
        try {
            return df.parse(date);
        } catch (ParseException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    private List<Integer> getNumbers(String[] input) {
        List<Integer> numbers = new ArrayList();
        numbers.add(Integer.valueOf(input[1]));
        numbers.add(Integer.valueOf(input[2]));
        numbers.add(Integer.valueOf(input[3]));
        numbers.add(Integer.valueOf(input[4]));
        numbers.add(Integer.valueOf(input[5]));
        numbers.add(Integer.valueOf(input[6]));
        return numbers;
    }

    private void validateDate(Date date) {
        dateEligibility.verify(date);
    }
}
