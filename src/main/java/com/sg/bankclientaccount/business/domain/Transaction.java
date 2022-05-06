package com.sg.bankclientaccount.business.domain;

import java.math.BigInteger;
import java.time.LocalDate;

public final class Transaction {
    private final BigInteger amount;
    private final BigInteger balance;
    private final TransactionType transactionType;
    private final LocalDate date;

    public Transaction(TransactionType transactionType, LocalDate date, BigInteger amount, BigInteger balance) {
        this.transactionType = transactionType;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public LocalDate getDate() {
        return date;
    }


    public TransactionType getTransactionType() {
        return transactionType;
    }



}
