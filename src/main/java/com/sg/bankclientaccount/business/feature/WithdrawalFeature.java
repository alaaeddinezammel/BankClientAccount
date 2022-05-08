package com.sg.bankclientaccount.business.feature;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.domain.TransactionType;
import com.sg.bankclientaccount.business.exception.BalanceNegativeWithdrawalException;
import com.sg.bankclientaccount.business.exception.NegativeAmountException;
import com.sg.bankclientaccount.business.port.input.WithdrawalPortInput;
import com.sg.bankclientaccount.business.port.output.transaction.TransactionRepositoryPortOutput;
import java.math.BigInteger;
import java.time.LocalDate;

public class WithdrawalFeature implements WithdrawalPortInput {

  private final TransactionRepositoryPortOutput transactionRepositoryOutput;

  public WithdrawalFeature(TransactionRepositoryPortOutput transactionRepositoryOutput) {
    this.transactionRepositoryOutput = transactionRepositoryOutput;
  }

  private BigInteger calculateAndGetCurrentBalance() {
    return transactionRepositoryOutput.findAllTransactions().stream()
        .map(Transaction::getAmount)
        .reduce(BigInteger::add)
        .orElse(null);
  }

  public void withdrawal(BigInteger withdrawalAmount)
      throws NegativeAmountException, BalanceNegativeWithdrawalException {
    if (withdrawalAmount.compareTo(BigInteger.ZERO) < 0) {
      throw new NegativeAmountException("Impossible to make a negative transaction");
    }

    final BigInteger currentBalance = calculateAndGetCurrentBalance();

    // if withdrawalAmount is greater than the balance
    if (withdrawalAmount.compareTo(currentBalance) > 0) {
      throw new BalanceNegativeWithdrawalException("The account Balance, is negative.");
    }

    Transaction transaction = new Transaction(
        TransactionType.WITHDRAWAL,
        LocalDate.now(),
        withdrawalAmount.negate(),
        currentBalance.subtract(withdrawalAmount));

    transactionRepositoryOutput.saveTransaction(transaction);
  }


}

