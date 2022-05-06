package com.sg.bankclientaccount.business.feature;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.domain.TransactionType;
import com.sg.bankclientaccount.business.exception.NegativeAmountException;
import com.sg.bankclientaccount.business.port.output.TransactionRepositoryOutput;
import java.math.BigInteger;
import java.time.LocalDate;

public class WithdrawalFeature {

  private final TransactionRepositoryOutput transactionRepositoryOutput;

  public WithdrawalFeature(TransactionRepositoryOutput transactionRepositoryOutput) {
    this.transactionRepositoryOutput = transactionRepositoryOutput;
  }

  private BigInteger getCurrentBalance() {
    return transactionRepositoryOutput.findAllTransactions().stream()
        .map(Transaction::getAmount)
        .reduce(BigInteger::add)
        .get();
  }

  public void withdrawal(BigInteger withdrawalAmount) throws NegativeAmountException {
    if (withdrawalAmount.compareTo(BigInteger.ZERO) == -1) {
      throw new NegativeAmountException("Impossible to make a negative transaction");
    }

    final BigInteger currentBalance = getCurrentBalance();

    Transaction transaction = new Transaction(
        TransactionType.WITHDRAWL,
        LocalDate.now(),
        withdrawalAmount.negate(),
        currentBalance.subtract(withdrawalAmount));

    transactionRepositoryOutput.saveTransaction(transaction);
  }


}
