package com.sg.bankclientaccount;

import com.sg.bankclientaccount.adapter.history.HistoryFormatterAdapter;
import com.sg.bankclientaccount.adapter.history.HistoryPrinterAdapter;
import com.sg.bankclientaccount.adapter.transaction.TransactionFormatterAdapter;
import com.sg.bankclientaccount.adapter.transaction.TransactionRepositoryAdapter;
import com.sg.bankclientaccount.business.feature.DepositFeature;
import com.sg.bankclientaccount.business.feature.HistoryFeature;
import com.sg.bankclientaccount.business.feature.WithdrawalFeature;
import com.sg.bankclientaccount.business.port.input.DepositPortInput;
import com.sg.bankclientaccount.business.port.input.WithdrawalPortInput;
import java.math.BigInteger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankClientAccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankClientAccountApplication.class, args);

    TransactionRepositoryAdapter transactionRepositoryFeature = new TransactionRepositoryAdapter();
    HistoryPrinterAdapter statementPrinterFeature = new HistoryPrinterAdapter();
    TransactionFormatterAdapter transactionFormatterFeature = new TransactionFormatterAdapter();

    HistoryFormatterAdapter statementFormatterFeature = new HistoryFormatterAdapter(
        statementPrinterFeature, transactionFormatterFeature);

    DepositPortInput depositFeature = new DepositFeature(transactionRepositoryFeature);
    WithdrawalPortInput withdrawFeature = new WithdrawalFeature(transactionRepositoryFeature);
    HistoryFeature printStatementFeature = new HistoryFeature(transactionRepositoryFeature,
        statementFormatterFeature);

    depositFeature.deposit(BigInteger.valueOf(1600));
    withdrawFeature.withdrawal(BigInteger.valueOf(600));
    withdrawFeature.withdrawal(BigInteger.valueOf(50));
    depositFeature.deposit(BigInteger.valueOf(5000));

    printStatementFeature.findOperations();
  }

}
