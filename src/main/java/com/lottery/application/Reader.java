package com.lottery.application;


import com.lottery.domain.Bet;

public interface Reader {

    boolean hasNext();

    Bet next();

}
