package ru.itmo.banks.bank;

import ru.itmo.banks.notification.Notification;

public class Bank {
    private String name;
    private float limitCredit;
    public Bank(String name, float bankInterest, float commission, float unverified, float limitCredit){
        this.name = name;
        this.bankInterest = bankInterest;
        this.commission = commission;
        this.unverified = unverified;
        this.limitCredit = limitCredit;
    }

    private float bankInterest;
    private float commission;
    private float unverified;

    public float getBankInterest() {
        return bankInterest;
    }

    public void setBankInterest(float bankInterest) {
        this.bankInterest = bankInterest;
    }
    public float getLimitCredit() {
        return limitCredit;
    }
    public float getCommission() {
        return commission;
    }
    public float getUnverified() {
        return unverified;
    }
    public void changeLimitCredit(float newLimitCredit){
        this.limitCredit = newLimitCredit;
        Notification.notifyChangeLimitCredit();
    }
    public void changeBankInterest(float newBankInterest){
        this.bankInterest = newBankInterest;
        Notification.notifyChangeBankInterest();
    }

}
