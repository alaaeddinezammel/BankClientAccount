package com.sg.bankclientaccount.business.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigInteger;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TransactionTest {

  @Test
  void testConstructor() {

    //when
    LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
    BigInteger valueOfResult = BigInteger.valueOf(42L);
    BigInteger valueOfResultCompareObject = BigInteger.valueOf(42L);
    //given
    Transaction actualTransaction = new Transaction(TransactionType.DEPOSIT, ofEpochDayResult,
        valueOfResult,
        valueOfResultCompareObject);

    BigInteger amount = actualTransaction.getAmount();
    BigInteger balance = actualTransaction.getBalance();
    //then
    assertSame(valueOfResult, amount);
    assertEquals(valueOfResultCompareObject, amount);
    assertSame(valueOfResultCompareObject, balance);
    assertEquals(amount, balance);
    assertSame(ofEpochDayResult, actualTransaction.getDate());
    assertEquals(TransactionType.DEPOSIT, actualTransaction.getTransactionType());
  }


  @Test
  void testEquals() {
    //when
    LocalDate date = LocalDate.ofEpochDay(1L);
    BigInteger amount = BigInteger.valueOf(42L);
    //given
    Transaction transaction = new Transaction(TransactionType.DEPOSIT, date, amount,
        BigInteger.valueOf(42L));
    var expectedHashCodeResult = transaction.hashCode();

    //then
    assertEquals(transaction, transaction);
    assertEquals(expectedHashCodeResult, transaction.hashCode());
  }


}