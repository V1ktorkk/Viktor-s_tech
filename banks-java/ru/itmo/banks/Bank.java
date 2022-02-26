package ru.itmo.banks;

import static ru.itmo.Notification.Notification.notifyChangeBankInterest;
import static ru.itmo.Notification.Notification.notifyChangeLimitCredit;

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
    public void ChangeLimitCredit(float newLimitCredit){
        this.limitCredit = newLimitCredit;
        notifyChangeLimitCredit();
    }
    public void changeBankInterest(float newBankInterest){
        this.bankInterest = newBankInterest;
        notifyChangeBankInterest();
    }

}
