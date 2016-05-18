package com.lottery.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BetSummary {

    private Bet bet;
    private Double winning;

    public BetSummary setBet(Bet bet) {
        this.bet = bet;
        return this;
    }

    public BetSummary setWinning(Double winning) {
        this.winning = winning;
        return this;
    }

    public Bet getBet() {
        return bet;
    }

    public Double getWinning() {
        return winning;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
