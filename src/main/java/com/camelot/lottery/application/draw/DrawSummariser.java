package com.camelot.lottery.application.draw;

import com.camelot.lottery.domain.Bet;
import com.camelot.lottery.domain.BetSummary;
import com.camelot.lottery.domain.Draw;
import com.camelot.lottery.domain.DrawSummary;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DrawSummariser {

    public DrawSummary summarise(Draw draw, List<Bet> bets) {
        List<BetSummary> betSummaries = summariseBets(draw, bets);

        return new DrawSummary()
                .setDraw(draw)
                .setBetSummaries(betSummaries);
    }

    private List<BetSummary> summariseBets(Draw draw, List<Bet> bets) {
        List<BetSummary> betSummaries = new ArrayList();
        for (Bet bet : bets) {
            BetSummary betSummary = summariseBet(bet, draw);
            betSummaries.add(betSummary);
        }
        return betSummaries;
    }


    private BetSummary summariseBet(Bet bet, Draw draw) {
        Double winning = calculateWinning(bet, draw);

        return new BetSummary()
                .setBet(bet)
                .setWinning(winning);
    }

    // TODO extract calculation into separate class
    private Double calculateWinning(Bet bet, Draw draw) {

        List<Integer> betNumbers = bet.getNumbers();
        List<Integer> drawNumbers = draw.getNumbers();

        if (bingo(betNumbers, drawNumbers)) {
            return calculateBingoWinning(drawNumbers);
        }

        if (moreThenThree(betNumbers, drawNumbers)) {
            return calculateMoreThenThreeWinning(betNumbers, drawNumbers);
        }

        return calculateSum(drawNumbers);
    }

    private double calculateSum(List<Integer> drawNumbers) {
        double sum = 0.0;
        for (Integer drawNum : drawNumbers) {
            sum += drawNum.intValue();
        }
        return sum;
    }

    private double calculateMoreThenThreeWinning(List<Integer> betNumbers, List<Integer> drawNumbers) {
        List<Integer> guesses = (List<Integer>) CollectionUtils.intersection(betNumbers, drawNumbers);
        List<Integer> misses = (List<Integer>) CollectionUtils.subtract(betNumbers, guesses);

        double guessesWin = 10000.00 * guesses.size();
        double missesMultiplied = 1.0;

        for (Integer missNumber : misses) {
            missesMultiplied *= missNumber.intValue();
        }

        return guessesWin + missesMultiplied;
    }

    private double calculateBingoWinning(List<Integer> drawNumbers) {
        double sum = 0.0;
        for (Integer drawNum : drawNumbers) {
            sum += 10000 * drawNum.intValue();
        }
        return sum;
    }

    private boolean bingo(List<Integer> betNumbers, List<Integer> drawNumbers) {
        return betNumbers.containsAll(drawNumbers);
    }

    private boolean moreThenThree(List<Integer> betNumbers, List<Integer> drawNumbers) {
        return CollectionUtils.intersection(betNumbers, drawNumbers).size() > 2;
    }


}
