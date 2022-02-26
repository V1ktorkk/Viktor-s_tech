package ru.itmo.accounts;

import ru.itmo.models.Client;
import ru.itmo.models.Transaction;

public abstract class Account {

    public Account(float money, Client client) {
        this.money = money;
        this.client = client;
    }

    protected float money;
    protected Client client;

    public Client getClient() {
        return client;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public abstract void afterOneDay();

    public void withdrawMoney(float money) throws Exception {
        if (!this.client.IsVerify()) {
            if (money >= this.client.getBank().getUnverified()) {
                throw new Exception("You need to Verify your account before you withdraw money");
            }
        }
        if (this.money >= money) {
            this.money -= money;
        } else {
            throw new Exception("You haven't got money to withdraw");
        }
    }

    public void addMoneyToAccount(float money) {
        this.money += money;
    }

    public Transaction transferMoney(float money, Account account) throws Exception {
        if (!this.client.IsVerify()) {
            if (money >= this.client.getBank().getUnverified()) {
                throw new Exception("You need to verify your account before you transfer money");
            }
        }
        if (this.money >= money) {
            account.money += money;
            this.money -= money;
            return new Transaction(account, this, money);
        } else {
            throw new Exception("ru.itmo.ru.itmo.models.Client doesn't have enough money to transfer to another account");
        }
    }
}

