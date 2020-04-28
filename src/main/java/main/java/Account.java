package main.java;

import org.apache.log4j.Logger;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Account {

    private static final Logger log = Logger.getLogger(Account.class);


    private String id;
    private int money;

    public Account(int money) {
        id = UUID.randomUUID().toString();
        this.money = money;
        log.info(Thread.currentThread().getName() + ": Создан аккаунт " + id + ". На счету: " + money);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void withdraw(int amount) {
        this.money -= amount;
    }

    public void deposit(int amount) {
        this.money += amount;
    }
}
