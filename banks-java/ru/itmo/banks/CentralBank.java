package ru.itmo.banks;

import ru.itmo.accounts.Account;
import ru.itmo.accounts.CreditAccount;
import ru.itmo.accounts.DebitAccount;
import ru.itmo.accounts.DepositAccount;
import ru.itmo.models.Client;
import ru.itmo.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CentralBank {
    private String name;
    private List<Bank> banks;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    private final List<Transaction> transactions;
    public CentralBank(String name){
        this.name = name;
        this.banks = new ArrayList<Bank>();
        this.transactions = new ArrayList<Transaction>();
    }

    public void deleteBank(Bank bank){
        this.banks.remove(bank);
    }

    public Bank createBank(String name, float bankInterest, float commission, float unverified, float limitCredit){
        var bank = new Bank(name, bankInterest, commission, unverified, limitCredit);
        this.banks.add(bank);
        return bank;
    }
    public void cancelTransaction(Transaction transaction){
        transaction.cancelTransaction();
        this.transactions.remove(transaction);
    }

    public Transaction transactionsBetweenAccounts(Account recipient, Account sender, float money) throws Exception {
        var transaction = sender.transferMoney(money, recipient);
        this.transactions.add(transaction);
        return transaction;
    }

    public void addCreditAccount(Client client){
        addCreditAccount(client, 0);
    }
    public void addCreditAccount(Client client, float money){
        client.addAccount(new CreditAccount(client.getBank().getBankInterest(), money, client.getBank(), client, client.getBank().getLimitCredit()));
    }
    public void addDebitAccount(Client client){
        addCreditAccount(client, 0);
    }
    public void addDebitAccount(Client client, float money){
        client.addAccount(new DebitAccount(client.getBank().getBankInterest(), money, client));
    }
    public void addDepositAccount(Client client){
        addDepositAccount(client, 0);
    }
    public void addDepositAccount(Client client, float money){
        client.addAccount(new DepositAccount(money, client, client.getBank().getLimitCredit()));
    }
}
