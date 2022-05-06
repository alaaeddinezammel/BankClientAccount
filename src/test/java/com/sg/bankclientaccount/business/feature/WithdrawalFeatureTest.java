package com.sg.bankclientaccount.business.feature;


import static org.mockito.Mockito.verify;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.domain.TransactionType;
import com.sg.bankclientaccount.business.exception.NegativeAmountException;
import com.sg.bankclientaccount.business.port.output.TransactionRepositoryOutput;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class WithdrawalFeatureTest {

  private WithdrawalFeature withdrawalFeatureUnderTest;
  private TransactionRepositoryOutput transactionRepositoryOutputMock;

  @BeforeEach
  public void setUp() {
    this.transactionRepositoryOutputMock = Mockito.mock(TransactionRepositoryOutput.class);
    this.withdrawalFeatureUnderTest = new WithdrawalFeature(this.transactionRepositoryOutputMock);
  }

  @Test
  void shouldMakeAWithdrawal() throws NegativeAmountException {
    // when
    BigInteger moneyToRetrieve = BigInteger.valueOf(100);
    List<Transaction> transactions = List.of(
        new Transaction(TransactionType.DEPOSIT, LocalDate.now(), BigInteger.valueOf(100),
            BigInteger.valueOf(100)));
    // given
    ArgumentCaptor<Transaction> withdrawalArgumentCaptor = ArgumentCaptor.forClass(
        Transaction.class);
    Transaction withdrawal = withdrawalArgumentCaptor.getValue();

    // then
    verify(transactionRepositoryOutputMock).saveTransaction(withdrawalArgumentCaptor.capture());
    verify(transactionRepositoryOutputMock).saveTransaction(withdrawal);
  }

}