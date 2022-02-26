package ru.itmo.models;

import ru.itmo.accounts.Account;
import ru.itmo.banks.Bank;

import java.util.ArrayList;

public class Client{
    private String name;
    private String adress;
    private int id;
    private Bank bank;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private ArrayList<String> messages  = new ArrayList<String>();

    public Client(String name, Bank bank){
        this.name = name;
        this.bank = bank;
    }

    public Client(String name, String adress, Bank bank){
        this.name = name;
        this.adress = adress;
        this.bank = bank;
    }

    public Client(String name, int id, Bank bank){
        this.name = name;
        this.id = id;
        this.bank = bank;
    }
    public Client(String name, String adress, int id, Bank bank){
        this.name = name;
        this.adress = adress;
        this.id = id;
        this.bank = bank;
    }
    public boolean IsVerify(){
        return !(name.isEmpty() || adress.isEmpty() || bank == null);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public Bank getBank() {
        return bank;
    }
    public void addAccount(Account account){
        accounts.add(account);
    }
    public void addMessage(String message){
        messages.add(message);
    }
}
