package com.sg.bankclientaccount.adapter;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.port.output.TransactionRepositoryPortOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepositoryAdapter  implements TransactionRepositoryPortOutput {

// TODO: 5/6/2022  -to implement
  public void saveTransaction(Transaction transaction) {
    
  }

// TODO: 5/6/2022  -to implement
  public List<Transaction> findAllTransactions() {
    return Collections.emptyList();
  }
}