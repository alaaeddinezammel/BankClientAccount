package com.sg.bankclientaccount.adapter;

import com.sg.bankclientaccount.business.port.output.HistoryPrinterPortOutput;

public class HistoryPrinterAdapter implements HistoryPrinterPortOutput {


  @Override
  public void print(String text) {
    System.out.println(text);
  }

}