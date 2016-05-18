package com.camelot.lottery.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class Bet {

    private Date date;
    private List<Integer> numbers;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Bet setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Bet setDate(Date date) {
        this.date = date;
        return this;
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
