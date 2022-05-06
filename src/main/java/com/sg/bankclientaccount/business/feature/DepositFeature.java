package com.sg.bankclientaccount.business.feature;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.domain.TransactionType;
import com.sg.bankclientaccount.business.exception.NegativeAmountException;
import com.sg.bankclientaccount.business.port.input.DepositPortInput;
import com.sg.bankclientaccount.business.port.output.TransactionRepositoryOutput;
import java.math.BigInteger;
import java.time.LocalDate;

public class DepositFeature implements DepositPortInput {

    private final TransactionRepositoryOutput transactionRepository;

    public DepositFeature(TransactionRepositoryOutput transactionRepositoryAction) {
        this.transactionRepository = transactionRepositoryAction;
    }
  @Override
  public void deposit(BigInteger depositAmount) throws NegativeAmountException {
    if (depositAmount.compareTo(BigInteger.ZERO) < 0) {
      throw new NegativeAmountException("Impossible to make a negative transaction");
    }

    final BigInteger currentBalance = GetCurrentBalance();
    Transaction transaction = new Transaction(
        TransactionType.DEPOSIT,
        LocalDate.now(),
        depositAmount,
        currentBalance.add(depositAmount));
    transactionRepository.saveTransaction(transaction);
  }
  private BigInteger GetCurrentBalance() {
    return transactionRepository.findAllTransactions().stream()
        .map(Transaction::getAmount)
        .reduce(BigInteger::add)
        .orElse(BigInteger.ZERO);
  }

}