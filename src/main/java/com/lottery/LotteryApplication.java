package com.lottery;

import com.lottery.application.GameController;
import com.lottery.application.Reader;
import com.lottery.infrastructure.ConsoleReader;
import com.lottery.infrastructure.ConsoleWriter;
import com.lottery.application.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LotteryApplication implements CommandLineRunner {

    @Bean
    public Reader consoleReader() {
        return new ConsoleReader(System.in);
    }

    @Bean
    public Writer consoleWriter() {
        return new ConsoleWriter(System.out);
    }

    @Autowired
    private GameController gameController;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n\nPlease place bets in format \"dd/MM/yyyy 1 2 3 4 5 6");
        System.out.println("Type in 'q' when you are done with bets. It will print out draws.\n\n");
        gameController.start();
    }

    public static void main(String[] args) {
        SpringApplication.run(LotteryApplication.class, args);
    }
}
