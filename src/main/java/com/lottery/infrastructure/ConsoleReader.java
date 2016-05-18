package com.lottery.infrastructure;

import com.lottery.application.Reader;
import com.lottery.domain.Bet;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleReader implements Reader {

    private BetBuilder betBuilder;
    private Scanner scanner;

    private String nextLine;

    public ConsoleReader(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        this.betBuilder = new BetBuilder();
    }

    @Override
    public boolean hasNext() {
        this.nextLine = scanner.nextLine();
        return !nextLine.equals("q");
    }

    @Override
    public Bet next() {
        return buildBet(nextLine);
    }

    private Bet buildBet(String input) {
        String[] parsedBet = new BetParser().parse(input);

        return betBuilder.build(parsedBet);
    }

    public void setBetBuilder(BetBuilder betBuilder) {
        this.betBuilder = betBuilder;
    }
}
