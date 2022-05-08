package com.sg.bankclientaccount.adapter.history;

import com.sg.bankclientaccount.business.port.output.history.HistoryPrinterPortOutput;

public class HistoryPrinterAdapter implements HistoryPrinterPortOutput {


  @Override
  public void print(String text) {
    System.out.println(text);
  }

}