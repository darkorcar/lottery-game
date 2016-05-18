package com.camelot.lottery.application;

import com.camelot.lottery.domain.DrawSummary;

public interface Writer {

    void write(DrawSummary drawSummary);

}
