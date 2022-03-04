
package ru.itmo.banks;

import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Before;
import ru.itmo.banks.account.Account;
import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.bank.CentralBank;
import ru.itmo.banks.model.Client;
import ru.itmo.banks.model.Transaction;

public class Test {
    private CentralBank centralBank;
    private Bank bank;
    private Client client;

    public Test() {
    }

    @Before
    public void setUp() {
        this.centralBank = new CentralBank("Central Bank");
        this.bank = this.centralBank.createBank("Sber", 3.0F, 10.0F, 100.0F, 30000.0F);
        this.client = new Client("Viktor", "Prosvet", this.bank);
    }

    @org.junit.Test
    @Description("check transactions betweenAccounts")
    public void checkingTransactions() throws Exception {
        this.centralBank.addCreditAccount(this.client, 100.0F);
        this.centralBank.addDebitAccount(this.client, 300.0F);
        this.centralBank.transactionsBetweenAccounts((Account)this.client.getAccounts().get(0), (Account)this.client.getAccounts().get(1), 10.0F);
        Assert.assertTrue(((Account)this.client.getAccounts().get(0)).getMoney() == 110.0F);
        Assert.assertTrue(((Account)this.client.getAccounts().get(1)).getMoney() == 290.0F);
    }

    @org.junit.Test
    @Description("check withdraw money")
    public void checkWithdraw() throws Exception {
        this.centralBank.addCreditAccount(this.client, 100.0F);
        ((Account)this.client.getAccounts().get(0)).withdrawMoney(90.0F);
        Assert.assertTrue(((Account)this.client.getAccounts().get(0)).getMoney() == 10.0F);
    }

    @org.junit.Test
    @Description("checking add money")
    public void CheckAddMoney() throws Exception {
        this.centralBank.addCreditAccount(this.client, 100.0F);
        ((Account)this.client.getAccounts().get(0)).addMoneyToAccount(100.0F);
        Assert.assertTrue(((Account)this.client.getAccounts().get(0)).getMoney() == 200.0F);
    }

    @org.junit.Test
    @Description("checking cancel transaction")
    public void CheckCancelTransaction() throws Exception {
        this.centralBank.addCreditAccount(this.client, 100.0F);
        this.centralBank.addDebitAccount(this.client, 300.0F);
        this.centralBank.transactionsBetweenAccounts((Account)this.client.getAccounts().get(0), (Account)this.client.getAccounts().get(1), 10.0F);
        this.centralBank.cancelTransaction((Transaction)this.centralBank.getTransactions().get(0));
        Assert.assertTrue(((Account)this.client.getAccounts().get(0)).getMoney() == 100.0F);
        Assert.assertTrue(((Account)this.client.getAccounts().get(1)).getMoney() == 300.0F);
    }

    @org.junit.Test
    @Description("checking skip time")
    public void CheckSkipTime() throws Exception {
        this.centralBank.addDebitAccount(this.client, 100.0F);

        for(int i = 1; i < 40; ++i) {
            ((Account)this.client.getAccounts().get(0)).afterOneDay();
        }

        Assert.assertTrue((double)Math.abs(((Account)this.client.getAccounts().get(0)).getMoney() - 124.657F) < 0.01D);
    }
}

