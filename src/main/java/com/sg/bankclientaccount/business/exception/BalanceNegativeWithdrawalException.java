package com.sg.bankclientaccount.business.exception;

public class BalanceNegativeWithdrawalException extends RuntimeException {

  public BalanceNegativeWithdrawalException(String s) {
    super(s);
  }
}