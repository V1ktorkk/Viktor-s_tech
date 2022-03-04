package ru.itmo.banks.account;

import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.model.Client;
import ru.itmo.banks.tool.BankException;

public class CreditAccount extends Account {
    public float getPercent() {
        return percent;
    }

    private float percent;
    private float creditLimit;
    private float moneyAccount;
    private float daysPercent;

    public CreditAccount(float percent, float money, Bank bank, Client client, float creditLimit) {
        super(money, client);
        this.percent = percent;
        this.creditLimit = creditLimit;
    }

    public void withdrawMoney(float money) {
        if (super.money - money >= -this.creditLimit) {
            super.money -= money;
        } else {
            throw new BankException("Client hasn't have money to withdraw");
        }
    }

    @Override
    public void afterOneDay() {
        if (super.money < 0) {
            this.moneyAccount -= (super.money * super.client.getBank().getBankInterest()) / 36500;
            this.daysPercent++;
            if (this.daysPercent == 30) {
                this.daysPercent = 0;
                afterOneMonth();
            }
        }
    }

    private void afterOneMonth() {
        super.money -= this.moneyAccount;
    }
}
