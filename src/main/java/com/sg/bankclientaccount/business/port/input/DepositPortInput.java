package com.sg.bankclientaccount.business.port.input;


import java.math.BigInteger;

public interface DepositPortInput {
    void deposit(BigInteger depositAmount);
}
