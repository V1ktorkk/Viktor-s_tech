package ru.itmo.banks.account;

import ru.itmo.banks.model.Client;
import ru.itmo.banks.model.Transaction;
import ru.itmo.banks.tool.BankException;

public class DepositAccount extends Account {
    private float moneyAccount;
    private int daysPercent;
    private float limit;
    private int time = 0;

    public DepositAccount(float money, Client client, float limit) {
        super(money, client);
        this.daysPercent = 0;
        this.moneyAccount = 0;
        this.limit = limit;
    }

    public void withdrawMoney(float money) {
        if (this.time >= this.limit) {
            super.withdrawMoney(money);
        }
    }

    @Override
    public Transaction transferMoney(float money, Account account) {
        if (this.time >= this.limit) {
            return super.transferMoney(money, account);
        } else {
            throw new BankException("the limit hasn't expired");
        }
    }

    @Override
    public void afterOneDay() {
        this.time++;
        if (this.time >= this.limit) return;
        this.moneyAccount += (super.money * client.getBank().getBankInterest()) / 365;
        this.daysPercent++;
        if (this.daysPercent != 30) return;
        this.daysPercent = 0;
        afterOneMonth();
    }

    private void afterOneMonth() {
        super.money += this.moneyAccount;
    }
}
