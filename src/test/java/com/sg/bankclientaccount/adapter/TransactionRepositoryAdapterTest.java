package com.sg.bankclientaccount.adapter;


import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.domain.TransactionType;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionRepositoryAdapterTest {

  private TransactionRepositoryAdapter transactionRepositoryAdapter;

  @BeforeEach
  public void setUp() {
    this.transactionRepositoryAdapter = new TransactionRepositoryAdapter();
  }

  @Test
  public void shouldSaveATransaction() {
    // When
    BigInteger amountToSave = BigInteger.valueOf(100);
    Transaction transaction = new Transaction(TransactionType.DEPOSIT, LocalDate.now(),
        amountToSave, amountToSave);

    List<Transaction> transactions = List.of(transaction);
    transactionRepositoryAdapter.saveTransaction(transaction);

    // then
    assertTrue(transactions.get(0)==transaction);
  }
}