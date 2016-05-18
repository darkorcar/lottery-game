package com.camelot.lottery.application;


import com.camelot.lottery.domain.Bet;

public interface Reader {

    boolean hasNext();

    Bet next();

}
