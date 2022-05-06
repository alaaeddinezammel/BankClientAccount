package com.sg.bankclientaccount.business.exception;

public class NegativeAmountException extends RuntimeException {
  public NegativeAmountException(String message){
    super(message);
  }
}
