package ru.itmo.Test;

import ru.itmo.banks.Bank;
import ru.itmo.banks.CentralBank;
import ru.itmo.models.Client;

public class Test {
    private static CentralBank centralBank;
    private static Bank bank;
    public static void main(String[] args) throws Exception {
        centralBank = new CentralBank("Central Bank");
        bank = centralBank.createBank("Sber", 3.0f, 10.0f, 100.0f, 30000.0f);
        System.out.println(checkTransactionsBetweenAccounts());
        System.out.println(checkWithdrawMoney());
        System.out.println(checkAddMoney());
        System.out.println(checkCancelTransaction());
        System.out.println(checkSkipTime());
    }

    private static boolean checkTransactionsBetweenAccounts() throws Exception {
        var client = new Client("Viktor", "Prosvet", bank);
        centralBank.addCreditAccount(client, 100.0f);
        centralBank.addDebitAccount(client, 300.0f);
        centralBank.transactionsBetweenAccounts(client.getAccounts().get(0), client.getAccounts().get(1), 10.0f);
        if ((client.getAccounts().get(0).getMoney() == 110.0f) && (client.getAccounts().get(1).getMoney() == 290.0f))
            return true;
        else
            return false;
    }
    private static boolean checkWithdrawMoney() throws Exception {
        var client = new Client("Viktor", "Prosvet", bank);
        centralBank.addCreditAccount(client, 100.0f);
        client.getAccounts().get(0).withdrawMoney(90.0f);
        if (client.getAccounts().get(0).getMoney() == 10.0f)
            return true;
        else
            return false;
    }

    private static boolean checkAddMoney(){
        var client = new Client("Viktor", "Prosvet", bank);
        centralBank.addCreditAccount(client, 100.0f);
        client.getAccounts().get(0).addMoneyToAccount(100.0f);
        if (client.getAccounts().get(0).getMoney() == 200.0f)
            return true;
        else
            return false;
    }
    private static boolean checkCancelTransaction() throws Exception {
        var client = new Client("Viktor", "Prosvet", bank);
        centralBank.addCreditAccount(client, 100.0f);
        centralBank.addDebitAccount(client, 300.0f);
        centralBank.transactionsBetweenAccounts(client.getAccounts().get(0), client.getAccounts().get(1), 10.0f);
        centralBank.cancelTransaction(centralBank.getTransactions().get(1));
        if ((client.getAccounts().get(0).getMoney() == 100.0f) && (client.getAccounts().get(1).getMoney()== 300.0f))
            return true;
        else
            return false;
    }
    private static boolean checkSkipTime(){
        var client = new Client("Viktor", "Prosvet", bank);
        centralBank.addDebitAccount(client, 100.0f);
        for (int i = 1; i < 40; i++){
            client.getAccounts().get(0).afterOneDay();
        }
        if (Math.abs(client.getAccounts().get(0).getMoney() - 124.657f) < 0.001)
            return true;
        else
            return false;
    }

}
