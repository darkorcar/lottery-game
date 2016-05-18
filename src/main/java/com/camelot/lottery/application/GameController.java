package com.camelot.lottery.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameController {

    private Reader reader;

    private Lottery lottery;

    @Autowired
    public GameController(Reader reader, Lottery lottery) {
        this.reader = reader;
        this.lottery = lottery;
    }

    public void start() {

        readBets();

        lottery.run();
    }

    private void readBets() {
        while (reader.hasNext()) {
            try {
                lottery.add(reader.next());
            } catch (InvalidInputException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

}
