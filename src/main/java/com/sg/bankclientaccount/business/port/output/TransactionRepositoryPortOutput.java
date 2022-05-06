package com.sg.bankclientaccount.business.port.output;


import com.sg.bankclientaccount.business.domain.Transaction;
import java.util.List;

public interface TransactionRepositoryPortOutput {

  void saveTransaction(Transaction transaction);

  List<Transaction> findAllTransactions();
}
