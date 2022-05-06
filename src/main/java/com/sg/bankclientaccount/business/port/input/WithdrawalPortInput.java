package com.sg.bankclientaccount.business.port.input;

import com.sg.bankclientaccount.business.exception.BalanceNegativeWithdrawalException;
import com.sg.bankclientaccount.business.exception.NegativeAmountException;
import java.math.BigInteger;

public interface WithdrawalPortInput {

  void withdrawal(BigInteger withdrawAmount)
      throws NegativeAmountException, BalanceNegativeWithdrawalException;
}