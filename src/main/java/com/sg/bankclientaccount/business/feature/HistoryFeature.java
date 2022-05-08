package com.sg.bankclientaccount.business.feature;

import com.sg.bankclientaccount.business.port.input.HistoryPortInput;
import com.sg.bankclientaccount.business.port.output.HistoryPrinterPortOutput;

public class HistoryFeature implements HistoryPortInput {

  private static final String OPERATIONS_HEADER = "OPERATION | DATE | AMOUNT | BALANCE";

  private final HistoryPrinterPortOutput historyPrinterPortOutput;

  public HistoryFeature(HistoryPrinterPortOutput historyPrinterPortOutput) {
    this.historyPrinterPortOutput = historyPrinterPortOutput;
  }

  @Override
  public void findOperations() {
    historyPrinterPortOutput.print(OPERATIONS_HEADER);

  }
}
