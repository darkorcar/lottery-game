package com.lottery.infrastructure;

import com.lottery.application.InvalidInputException;

public class BetParser {

    private static String delimiter = "(\\s+|$)";
    private static String date = "(\\d\\d\\/\\d\\d\\/\\d\\d\\d\\d)";
    private static String numberBetween1and60 = "([1-9]|[1-5][0-9]|60)";
    private static String numbersToBetOn = "{6}";

    private static String inputValidationRegExp =
            "^" + date + delimiter + "(" + numberBetween1and60 + delimiter + ")" + numbersToBetOn + "$";

    public String[] parse(String input) {
        validate(input);
        return input.split(" ");
    }

    private static void validate(String input) {

        System.out.println("input - " + input);

        if (!input.matches(inputValidationRegExp)) {
            System.out.println("failing");
            throw new InvalidInputException("Invalid bet parameters.");
        }
    }
}
