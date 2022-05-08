package com.sg.bankclientaccount.business.port.output.history;

import com.sg.bankclientaccount.business.domain.Transaction;
import java.util.List;

public interface HistoryFormatterOutput {

  void print(List<Transaction> transactions);
}
