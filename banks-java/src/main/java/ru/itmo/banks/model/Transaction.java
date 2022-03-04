package ru.itmo.banks.model;

import ru.itmo.banks.account.Account;

public class Transaction {
    private Account recipient;
    private Account sender;
    private float money;

    public Transaction(Account recipient, Account sender, float money) {
        this.recipient = recipient;
        this.sender = sender;
        this.money = money;
    }

    public void cancelTransaction() {
        this.recipient.setMoney(this.recipient.getMoney() - money);
        this.sender.setMoney(this.sender.getMoney() + money);

    }
}