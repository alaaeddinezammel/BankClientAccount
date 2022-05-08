package com.sg.bankclientaccount.adapter.transaction;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.port.output.transaction.TransactionRepositoryPortOutput;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryAdapter implements TransactionRepositoryPortOutput {

  private final List<Transaction> transactions = new ArrayList<>();

  public void saveTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  public List<Transaction> findAllTransactions() {
    return transactions;
  }
}