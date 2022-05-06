package com.sg.bankclientaccount.business.port;


import java.math.BigInteger;

public interface DepositPort {
    void deposit(BigInteger depositAmount);
}
