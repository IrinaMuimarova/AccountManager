package main.java;

import org.apache.log4j.Logger;

public class AccountService {
    private static final Logger log = Logger.getLogger(AccountService.class);
    public static final int COUNT_ACCOUNT = 10;
    public static final int MONEY_ACCOUNT = 10000;

    private Account[] accounts = new Account[COUNT_ACCOUNT];
    int countTransaction = 0;

    public AccountService() {
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(MONEY_ACCOUNT);
        }
    }

    public synchronized void transferMoney(int from, int to, int amount) {
        if (amount <= accounts[from].getMoney()) {
            accounts[from].withdraw(amount);
            accounts[to].deposit(amount);

            log.info(Thread.currentThread().getName() + ": Перевод № " + (++countTransaction) + ". Переведено " + amount + " со счета " + accounts[from].getId() + " на счет " + accounts[to].getId());
            log.info(Thread.currentThread().getName() + ": Счет " + accounts[from].getId() + " = " + accounts[from].getMoney());
            log.info(Thread.currentThread().getName() + ": Счет " + accounts[to].getId() + " = " + accounts[to].getMoney());
            log.info(Thread.currentThread().getName() + ": Сумма денег на всех счетах =  " + getTotalBalance());

        } else {
            log.error(Thread.currentThread().getName() + ": Недостаточно средств для перевода.");
        }
    }

    public synchronized int getTotalBalance() {
        int sum = 0;

        for (Account account : accounts) {
            sum += account.getMoney();
        }

        return sum;
    }
}
