package ru.itmo.banks.console.ui;

import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.bank.CentralBank;
import ru.itmo.banks.model.Client;

import java.util.Scanner;

public class Main {
    private static Client client;
    private static final CentralBank centralbank = new CentralBank("Главный банк");

    public Main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, you need to create client");
        var name = scanner.nextLine();
        var adress = scanner.nextLine();
        System.out.println("You need to choose one of the proposed banks: ");
        System.out.println("If you want Sber bank press 1");
        System.out.println("If you want Vtb bank press 2");
        System.out.println("If you want Tinkoff press 3");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> client = new Client(name, adress, new Bank("Sber", 3.0f, 10.0f, 10000.0f, 1000000.0f));
            case 2 -> client = new Client(name, adress, new Bank("Vtb", 5.0f, 3.0f, 1000.0f, 33330000.0f));
            case 3 -> client = new Client(name, adress, new Bank("Tinkoff", 10.0f, 20.0f, 100.0f, 1000.0f));
            default -> client = new Client(name, adress, new Bank("Sber", 3.0f, 10.0f, 10000.0f, 1000000.0f));
        }

        System.out.println("You need to choose account: ");
        System.out.println("If you want Deposit Account press 1");
        System.out.println("If you want Debit Account press 2");
        System.out.println("If you want Credit Account press 3");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> centralbank.addDepositAccount(client);
            case 2 -> centralbank.addDebitAccount(client);
            case 3 -> centralbank.addCreditAccount(client);
            default -> centralbank.addDepositAccount(client);
        }

        boolean inloop = true;
        while (inloop) {
            System.out.println("what you want to do?");
            System.out.println("Withdraw money press 1");
            System.out.println("Add money to account press 2");
            System.out.println("If you want to cancel transaction press 3");
            System.out.println("If you want to exit press 4");
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1 -> client.getAccounts().get(0).withdrawMoney(Float.parseFloat(scanner.nextLine()));
                case 2 -> client.getAccounts().get(0).addMoneyToAccount(Float.parseFloat(scanner.nextLine()));
                case 3 -> centralbank.cancelTransaction(centralbank.getTransactions().get(Integer.parseInt(scanner.nextLine())));
                case 4 -> inloop = false;
            }
        }
    }
}
