package com.sg.bankclientaccount.adapter.history;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.port.output.history.HistoryFormatterOutput;
import com.sg.bankclientaccount.business.port.output.history.HistoryPrinterPortOutput;
import com.sg.bankclientaccount.business.port.output.transaction.TransactionFormatterOutput;
import java.util.Comparator;
import java.util.List;

public class HistoryFormatterAdapter implements HistoryFormatterOutput {

  private static final String STATEMENT_HEADER = "OPERATION | DATE | AMOUNT | BALANCE";

  private final HistoryPrinterPortOutput historyPrinterPortOutput;
  private final TransactionFormatterOutput transactionFormatterOutput;

  public HistoryFormatterAdapter(HistoryPrinterPortOutput historyFeature,
      TransactionFormatterOutput transactionFormatterOutput) {
    this.historyPrinterPortOutput = historyFeature;
    this.transactionFormatterOutput = transactionFormatterOutput;
  }

  @Override
  public void print(List<Transaction> transactions) {

    String operation = String.valueOf(printHeader())
        + printStatementLines(transactions);

    historyPrinterPortOutput.print(operation);
  }

  private StringBuilder printHeader() {
    StringBuilder header = new StringBuilder();
    return header.append(STATEMENT_HEADER);
  }

  private StringBuilder printStatementLines(List<Transaction> transactions) {
    StringBuilder statementLines = new StringBuilder();
    transactions.stream()
        .sorted(Comparator.comparing(Transaction::getDate).reversed())
        .forEach(transaction -> {
          statementLines.append("\n");
          statementLines.append(transactionFormatterOutput.format(transaction));
        });
    return statementLines;
  }


}


