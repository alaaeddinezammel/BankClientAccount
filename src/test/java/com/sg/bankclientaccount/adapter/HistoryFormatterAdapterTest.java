package com.sg.bankclientaccount.adapter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.domain.TransactionType;
import com.sg.bankclientaccount.business.port.output.HistoryPrinterPortOutput;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HistoryFormatterAdapterTest {

  private HistoryFormatterAdapter historyFormatterAdapter;
  private HistoryPrinterPortOutput historyPrinterPortOutput;
  private TransactionFormatterAdapter transactionFormatterAdapter;

  @BeforeEach
  void setUp() {
    this.historyPrinterPortOutput = Mockito.mock(HistoryPrinterPortOutput.class);
    this.transactionFormatterAdapter = mock(TransactionFormatterAdapter.class);
    this.historyFormatterAdapter = new HistoryFormatterAdapter(
        historyPrinterPortOutput,
        transactionFormatterAdapter);
  }

  @Test
  void shouldPrint() {

    //given
    doNothing().when(historyPrinterPortOutput).print(any());
    historyFormatterAdapter.print(new ArrayList<>());

    //then
    verify(historyPrinterPortOutput).print(any());
  }


  @Test
  void shouldPrintWhenDeposit() {
    //when
    LocalDate date = LocalDate.ofEpochDay(1L);
    BigInteger amount = BigInteger.valueOf(42L);
    ArrayList<Transaction> transactionList = new ArrayList<>();

    //given
    doNothing().when(historyPrinterPortOutput).print(any());
    when(transactionFormatterAdapter.format(any())).thenReturn("Format");
    transactionList.add(
        new Transaction(TransactionType.DEPOSIT, date, amount, BigInteger.valueOf(42L)));
    historyFormatterAdapter.print(transactionList);

    //then
    verify(historyPrinterPortOutput).print(any());
    verify(transactionFormatterAdapter).format(any());
  }


  @Test
  void shouldPrintWhenListOfTransactions() {
    //when
    LocalDate date = LocalDate.ofEpochDay(1L);
    BigInteger amount = BigInteger.valueOf(42L);
    ArrayList<Transaction> transactionList = new ArrayList<>();
    LocalDate dateObjectTwo = LocalDate.ofEpochDay(1L);
    BigInteger amountObjectTwo = BigInteger.valueOf(42L);

    //given
    transactionList.add(
        new Transaction(TransactionType.DEPOSIT, date, amount, BigInteger.valueOf(42L)));

    transactionList.add(
        new Transaction(TransactionType.DEPOSIT, dateObjectTwo, amountObjectTwo,
            BigInteger.valueOf(42L)));
    doNothing().when(historyPrinterPortOutput).print(any());
    when(transactionFormatterAdapter.format(any())).thenReturn("Format");
    historyFormatterAdapter.print(transactionList);

    //then
    verify(historyPrinterPortOutput).print(any());
    verify(transactionFormatterAdapter, atLeast(1)).format(any());
  }


}

