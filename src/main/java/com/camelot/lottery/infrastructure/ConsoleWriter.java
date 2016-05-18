package com.camelot.lottery.infrastructure;

import com.camelot.lottery.application.Writer;
import com.camelot.lottery.domain.BetSummary;
import com.camelot.lottery.domain.Draw;
import com.camelot.lottery.domain.DrawSummary;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ConsoleWriter implements Writer {

    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private PrintStream printStream;

    public ConsoleWriter(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void write(DrawSummary drawSummary) {
        writeDraw(drawSummary.getDraw());
        writeBets(drawSummary.getBetSummaries());
    }

    private void writeDraw(Draw draw) {
        String drawDate = df.format(draw.getDate());
        printStream.println("Draw date: [" + drawDate + "] numbers: " + draw.getNumbers());
    }

    private void writeBets(List<BetSummary> betSummaries) {
        printStream.println("Winnings:");
        for (BetSummary betSummary : betSummaries) {
            printStream.format("%-40s%s%20.1f%n",
                    betSummary.getBet().getNumbers(),
                    "prise:",
                    betSummary.getWinning());
        }
        printStream.println("------------------------------------------------------------------");
    }
}
