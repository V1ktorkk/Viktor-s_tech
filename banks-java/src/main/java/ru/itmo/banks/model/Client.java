package ru.itmo.banks.model;

import ru.itmo.banks.account.Account;
import ru.itmo.banks.bank.Bank;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String adress;
    private int id;
    private Bank bank;
    private List<Account> accounts = new ArrayList<Account>();
    private List<String> messages = new ArrayList<String>();

    public Client(String name, Bank bank) {
        this.name = name;
        this.bank = bank;
    }

    public Client(String name, String adress, Bank bank) {
        this.name = name;
        this.adress = adress;
        this.bank = bank;
    }

    public Client(String name, int id, Bank bank) {
        this.name = name;
        this.id = id;
        this.bank = bank;
    }

    public Client(String name, String adress, int id, Bank bank) {
        this.name = name;
        this.adress = adress;
        this.id = id;
        this.bank = bank;
    }

    public boolean isVerify() {
        return !(name.isEmpty() || adress.isEmpty() || bank == null);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Bank getBank() {
        return bank;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
