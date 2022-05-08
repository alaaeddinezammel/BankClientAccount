package com.sg.bankclientaccount.business.port.output;

import com.sg.bankclientaccount.business.domain.Transaction;

public interface TransactionFormatterOutput {

  String format(Transaction transaction);
}

