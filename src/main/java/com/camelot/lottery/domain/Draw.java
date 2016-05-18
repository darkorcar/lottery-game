package com.camelot.lottery.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class Draw {

    private Date date;
    private List<Integer> numbers;

    public Date getDate() {
        return date;
    }

    public Draw setDate(Date date) {
        this.date = date;
        return this;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Draw setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
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
