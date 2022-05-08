package com.sg.bankclientaccount.business.feature;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.domain.TransactionType;
import com.sg.bankclientaccount.business.port.output.history.HistoryFormatterOutput;
import com.sg.bankclientaccount.business.port.output.transaction.TransactionRepositoryPortOutput;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HistoryFeatureTest {

  private HistoryFeature historyFeatureUnderTest;

  private HistoryFormatterOutput historyFormatterOutput;

  private TransactionRepositoryPortOutput transactionRepositoryPortOutputMock;


  @BeforeEach
  void setUp() {
    this.transactionRepositoryPortOutputMock = Mockito.mock(TransactionRepositoryPortOutput.class);

    this.historyFormatterOutput = Mockito.mock(HistoryFormatterOutput.class);

    this.historyFeatureUnderTest = new HistoryFeature(transactionRepositoryPortOutputMock,
        historyFormatterOutput);
  }

  @Test
  void shouldPrintEmptyHistory() {
    // when
    var transactions = EMPTY_LIST;

    // given
    given(transactionRepositoryPortOutputMock.findAllTransactions()).willReturn(transactions);
    historyFeatureUnderTest.printHistory();

    // then
    verify(historyFormatterOutput).print(transactions);
  }


  @Test
  void shouldPrintHistoryWithOneTransaction() {
    // when
    Transaction transaction = new Transaction(TransactionType.DEPOSIT, LocalDate.of(2021, 11, 28),
        BigInteger.valueOf(1000), BigInteger.valueOf(1000));

    List<Transaction> transactions = List.of(transaction);
    // given
    given(transactionRepositoryPortOutputMock.findAllTransactions()).willReturn(transactions);

    historyFeatureUnderTest.printHistory();
    // then
    verify(historyFormatterOutput).print(transactions);
  }


}