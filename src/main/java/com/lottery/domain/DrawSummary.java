package com.lottery.domain;

import java.util.List;

public class DrawSummary {

    private Draw draw;
    private List<BetSummary> betSummaries;

    public Draw getDraw() {
        return draw;
    }

    public DrawSummary setDraw(Draw draw) {
        this.draw = draw;
        return this;
    }

    public List<BetSummary> getBetSummaries() {
        return betSummaries;
    }

    public DrawSummary setBetSummaries(List<BetSummary> betSummaries) {
        this.betSummaries = betSummaries;
        return this;
    }
}
