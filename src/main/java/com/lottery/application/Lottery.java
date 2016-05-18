package com.lottery.application;

import com.lottery.application.draw.DrawGenerator;
import com.lottery.application.draw.DrawSummariser;
import com.lottery.domain.Bet;
import com.lottery.domain.Draw;
import com.lottery.domain.DrawSummary;
import com.lottery.util.date.DateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Lottery {

    private List<Bet> bets = new ArrayList();

    private DrawGenerator drawGenerator;

    private DateProvider dateProvider;

    private DrawSummariser drawSummariser;

    private Writer writer;

    @Autowired
    public Lottery(DrawGenerator drawGenerator, DateProvider dateProvider, DrawSummariser drawSummariser, Writer writer) {
        this.drawGenerator = drawGenerator;
        this.dateProvider = dateProvider;
        this.drawSummariser = drawSummariser;
        this.writer = writer;
    }

    public void run() {
        List<Draw> draws = generateDraws(dateProvider.currentDate());

        for (Draw draw : draws) {
            DrawSummary drawSummary = summariseDraw(draw);

            writer.write(drawSummary);
        }
    }

    private List<Draw> generateDraws(Date date) {
        return drawGenerator.generateDraws(date);
    }

    public void add(Bet bet) {
        bets.add(bet);
    }

    private DrawSummary summariseDraw(Draw draw) {
        return drawSummariser.summarise(draw, bets);
    }
}
