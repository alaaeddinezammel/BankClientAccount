package com.sg.bankclientaccount.business.feature;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.exception.NegativeAmountException;
import com.sg.bankclientaccount.business.port.output.TransactionRepositoryOutput;
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
  void shouldMakeADeposit() throws NegativeAmountException {
    // when
    BigInteger amountToSave = BigInteger.valueOf(100);

    //given
    ArgumentCaptor<Transaction> depositArgumentCaptor = ArgumentCaptor.forClass(Transaction.class);
    given(TransactionRepositoryOutputMock.findAllTransactions()).willReturn(emptyList());

    depositFeatureUnderTest.deposit(amountToSave);
    verify(TransactionRepositoryOutputMock).saveTransaction(depositArgumentCaptor.capture());
    Transaction depositArgument = depositArgumentCaptor.getValue();

    // then
    verify(TransactionRepositoryOutputMock).saveTransaction(depositArgument);
  }

  @Test
  void shouldFailDepositWhenNegativeAmount() {
    // when
    BigInteger amountToSave = BigInteger.valueOf(-100);
    // given
    String expectedMessage = "Impossible to make a negative transaction";
    Exception exception = assertThrows(NegativeAmountException.class,
        () -> depositFeatureUnderTest.deposit(amountToSave));
    String actualMessage = exception.getMessage();
    // then
    assertTrue(actualMessage.contains(expectedMessage));
  }
}


