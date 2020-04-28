package main.java;

import org.apache.log4j.Logger;

public class Transaction implements Runnable {

    private static final Logger log = Logger.getLogger(Transaction.class);

    private AccountService accountService;
    private int fromAccount;

    public Transaction(AccountService accountService, int fromAccount) {
        this.accountService = accountService;
        this.fromAccount = fromAccount;
    }

    public void run() {
        while (accountService.countTransaction < 30) {
            try {
                long millis = (long) (Math.random() * (2000)) + 1000;;
                Thread.sleep(millis);
                log.info(Thread.currentThread().getName() + ": Засыпаю на " + millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int toAccount = (int) (Math.random() * AccountService.COUNT_ACCOUNT);

            if (toAccount == fromAccount) continue;

            int amount = (int) (Math.random() * AccountService.MONEY_ACCOUNT);

            if (amount == 0) continue;

            accountService.transferMoney(fromAccount, toAccount, amount);
        }
    }
}
