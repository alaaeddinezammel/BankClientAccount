package com.sg.bankclientaccount.adapter.transaction;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.port.output.transaction.TransactionFormatterOutput;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class TransactionFormatterAdapter implements TransactionFormatterOutput {

  private final static String SEPARATOR = " | ";
  private final static DateTimeFormatter DATE_FORMAT =
      new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy").toFormatter();

  @Override
  public String format(Transaction transaction) {
    return String.format("%s" + SEPARATOR + "%s" + SEPARATOR + "%d" + SEPARATOR + "%d",
        transaction.getTransactionType().toString(),
        transaction.getDate().format(DATE_FORMAT),
        transaction.getAmount(),
        transaction.getBalance());
  }
}