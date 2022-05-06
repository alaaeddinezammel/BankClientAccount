package com.sg.bankclientaccount.business.feature;

import static java.util.Collections.emptyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.port.TransactionRepositoryOutput;
import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class DepositFeatureTest {

  private DepositFeature depositFeatureUnderTest;

  private TransactionRepositoryOutput TransactionRepositoryOutputMock;

  @BeforeEach
  void setUp() {
    this.TransactionRepositoryOutputMock = Mockito.mock(TransactionRepositoryOutput.class);

    this.depositFeatureUnderTest = new DepositFeature(TransactionRepositoryOutputMock);
  }

  @Test
  void shouldMakeADeposit() {
    // when
    BigInteger amountToSave = BigInteger.valueOf(100);

    //given
    ArgumentCaptor<Transaction> depositArgumentCaptor = ArgumentCaptor.forClass(Transaction.class);
    given(TransactionRepositoryOutputMock.findAllTransactions()).willReturn(emptyList());

    Transaction depositArgument = depositArgumentCaptor.getValue();
    verify(TransactionRepositoryOutputMock).saveTransaction(depositArgumentCaptor.capture());

    // then
    verify(TransactionRepositoryOutputMock).saveTransaction(depositArgument);
  }


}

