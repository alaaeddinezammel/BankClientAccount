package com.sg.bankclientaccount.adapter;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.sg.bankclientaccount.adapter.transaction.TransactionRepositoryAdapter;
import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.domain.TransactionType;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionRepositoryAdapterTest {

  private TransactionRepositoryAdapter transactionRepositoryAdapterUnderTest;

  @BeforeEach
  public void setUp() {
    this.transactionRepositoryAdapterUnderTest = new TransactionRepositoryAdapter();
  }

  @Test
  public void shouldSaveATransaction() {
    // When
    BigInteger amountToSave = BigInteger.valueOf(100);
    Transaction transaction = new Transaction(TransactionType.DEPOSIT, LocalDate.now(),
        amountToSave, amountToSave);

    List<Transaction> transactions = List.of(transaction);
    transactionRepositoryAdapterUnderTest.saveTransaction(transaction);

    // then
    assertThat(transactions.get(0), equalTo(transaction));

  }


  @Test
  public void shouldReturnManySavedTransactions() {
    // When
    Transaction transactionOne = new Transaction(TransactionType.DEPOSIT,
        LocalDate.of(2002, 10, 26),
        BigInteger.valueOf(1100), BigInteger.valueOf(1100));
    Transaction transactionTwo = new Transaction(TransactionType.WITHDRAWAL,
        LocalDate.of(2021, 10, 30), BigInteger.valueOf(-130), BigInteger.valueOf(800));

    List<Transaction> transactions = transactionRepositoryAdapterUnderTest.findAllTransactions();

    transactionRepositoryAdapterUnderTest.saveTransaction(transactionOne);
    transactionRepositoryAdapterUnderTest.saveTransaction(transactionTwo);

    // Then
    assertThat(transactions.get(0), equalTo(transactionOne));
    assertThat(transactions.get(1), equalTo(transactionTwo));

  }

}