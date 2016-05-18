package com.camelot.lottery.infrastructure;

import java.util.*;

public class NumberGenerator {

    private Random random = new Random();

    public List<Integer> generate() {
        Set<Integer> numbers = new HashSet();

        while(numbers.size() < 6) {
            numbers.add(random.nextInt(60) + 1);
        }

        return new ArrayList(numbers);
    }

}
