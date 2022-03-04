package ru.itmo.banks.notification;

public class Notification {
    public static void notifyChangeLimitCredit() {
        System.out.println("Limit credit was changed");
    }
    public static void notifyChangeBankInterest() {
        System.out.println("Bank Interest was changed");
    }
}