package com.sg.bankclientaccount.business.feature;

import com.sg.bankclientaccount.business.domain.Transaction;
import com.sg.bankclientaccount.business.domain.TransactionType;
import com.sg.bankclientaccount.business.port.DepositPort;
import com.sg.bankclientaccount.business.port.TransactionRepositoryOutput;
import java.math.BigInteger;
import java.time.LocalDate;

public class DepositFeature implements DepositPort {

    private final TransactionRepositoryOutput transactionRepository;

    public DepositFeature(TransactionRepositoryOutput transactionRepositoryAction) {
        this.transactionRepository = transactionRepositoryAction;
    }
    @Override
    public void deposit(BigInteger depositAmount) {
  final BigInteger currentBalance = calculateAndGetCurrentBalance();
        Transaction transaction = new Transaction(
                TransactionType.DEPOSIT,
                LocalDate.now(),
                depositAmount,
                currentBalance.add(depositAmount));
        transactionRepository.saveTransaction(transaction);
    }
    private BigInteger calculateAndGetCurrentBalance() {
        return transactionRepository.findAllTransactions().stream()
                .map(Transaction::getAmount)
                .reduce(BigInteger::add)
                .orElse(BigInteger.ZERO);
    }

}