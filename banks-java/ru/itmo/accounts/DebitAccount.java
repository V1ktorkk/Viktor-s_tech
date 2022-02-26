package ru.itmo.accounts;

import ru.itmo.models.Client;

public class DebitAccount extends Account {
    private float percent;
    private float moneyAccount;
    private int daysPercent;

    public DebitAccount(float percent, float money, Client client) {
        super(money, client);
        this.percent = percent;
        this.moneyAccount = 0;
        this.daysPercent = 0;
    }
    @Override
    public void afterOneDay() {
        this.moneyAccount += (super.money * super.client.getBank().getBankInterest()) / 365;
        this.daysPercent ++;
        if (this.daysPercent != 30) return;
        this.daysPercent = 0;
        afterOneMonth();
    }
    private void afterOneMonth(){
        super.money += this.moneyAccount;
    }
}
