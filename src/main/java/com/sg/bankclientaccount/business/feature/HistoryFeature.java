package com.sg.bankclientaccount.business.feature;

import com.sg.bankclientaccount.business.port.input.HistoryPortInput;
import com.sg.bankclientaccount.business.port.output.HistoryFormatterOutput;
import com.sg.bankclientaccount.business.port.output.TransactionRepositoryPortOutput;

public class HistoryFeature implements HistoryPortInput {

  private static final String OPERATIONS_HEADER = "OPERATION | DATE | AMOUNT | BALANCE";

  private final TransactionRepositoryPortOutput transactionRepositoryPortOutput;
  private final HistoryFormatterOutput historyFormatterOutput;

  public HistoryFeature(TransactionRepositoryPortOutput transactionRepositoryPortOutput,
      HistoryFormatterOutput historyFormatterOutput) {
    this.transactionRepositoryPortOutput = transactionRepositoryPortOutput;
    this.historyFormatterOutput = historyFormatterOutput;
  }

  public void printHistory() {
    this.historyFormatterOutput.print(this.transactionRepositoryPortOutput.findAllTransactions());
  }

  @Override
  public void findOperations() {
    this.historyFormatterOutput.print(this.transactionRepositoryPortOutput.findAllTransactions());

  }
}
