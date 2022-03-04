package ru.itmo.banks.console.ui;

import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.bank.CentralBank;
import ru.itmo.banks.model.Client;

import java.util.Scanner;

public class main {
    private static Bank bank = new Bank("Sber", 3.0f, 10.0f, 10000.0f, 1000000.0f);
    private static Client client;
    private static final CentralBank centralbank = new CentralBank("Главный банк");

    public main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, you need to create client");
        var name = scanner.nextLine();
        var adress = scanner.nextLine();
        client = new Client(name, adress, bank);
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
        while (inloop)
        {
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
