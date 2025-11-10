/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bankdemo_4;

/**
 *
 * @author user
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account {
    private double balance;

    public Account(double initBalance) {
        this.balance = initBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Гроші успішно зараховано!");
        } else {
            System.out.println("❌ Сума повинна бути більшою за 0.");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("❌ Недостатньо коштів на рахунку!");
        } else if (amount <= 0) {
            System.out.println("❌ Сума повинна бути більшою за 0.");
        } else {
            balance -= amount;
            System.out.println("✅ Гроші успішно знято!");
        }
    }
}

class Customer {
    private String firstName;
    private String lastName;
    private List<Account> accounts;

    public Customer(String f, String l) {
        this.firstName = f;
        this.lastName = l;
        this.accounts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addAccount(Account acc) {
        accounts.add(acc);
    }

    public Account getAccount(int index) {
        if (index >= 0 && index < accounts.size()) {
            return accounts.get(index);
        }
        return null;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}

class Bank {
    private static List<Customer> customers = new ArrayList<>();

    public static void addCustomer(String f, String l) {
        customers.add(new Customer(f, l));
    }

    public static Customer getCustomer(int index) {
        if (index >= 0 && index < customers.size()) {
            return customers.get(index);
        }
        return null;
    }

    public static int getNumOfCustomers() {
        return customers.size();
    }

    public static List<Customer> getCustomers() {
        return customers;
    }
}

class CustomerReport {
    public void generateReport() {
        System.out.println("\n=== Звіт по клієнтах банку ===");
        for (int i = 0; i < Bank.getNumOfCustomers(); i++) {
            Customer c = Bank.getCustomer(i);
            System.out.println((i + 1) + ". " + c.getLastName() + " " + c.getFirstName());
            for (int j = 0; j < c.getNumberOfAccounts(); j++) {
                Account a = c.getAccount(j);
                System.out.printf("   Рахунок %d: %.2f грн\n", j + 1, a.getBalance());
            }
        }
        System.out.println("==============================\n");
    }
}

public class Bankdemo_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Створимо кілька клієнтів і рахунків
        Bank.addCustomer("Іван", "Петренко");
        Bank.getCustomer(0).addAccount(new Account(1200.0));

        Bank.addCustomer("Марія", "Коваленко");
        Bank.getCustomer(1).addAccount(new Account(800.0));

        Bank.addCustomer("Олег", "Сидоренко");
        Bank.getCustomer(2).addAccount(new Account(2500.0));

        boolean running = true;
        while (running) {
            System.out.println("=== ГОЛОВНЕ МЕНЮ БАНКУ ===");
            System.out.println("1. Переглянути звіт по клієнтах");
            System.out.println("2. Вибрати клієнта для роботи з рахунком");
            System.out.println("3. Вихід");
            System.out.print("Ваш вибір: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    CustomerReport report = new CustomerReport();
                    report.generateReport();
                }
                case 2 -> {
                    if (Bank.getNumOfCustomers() == 0) {
                        System.out.println("❌ У банку ще немає клієнтів.");
                        break;
                    }

                    System.out.println("\nОберіть клієнта:");
                    for (int i = 0; i < Bank.getNumOfCustomers(); i++) {
                        Customer c = Bank.getCustomer(i);
                        System.out.println((i + 1) + ". " + c.getLastName() + " " + c.getFirstName());
                    }

                    System.out.print("Введіть номер клієнта: ");
                    int clientIndex = sc.nextInt() - 1;

                    Customer selected = Bank.getCustomer(clientIndex);
                    if (selected == null) {
                        System.out.println("❌ Невірний номер клієнта.");
                        break;
                    }

                    System.out.println("\n=== Робота з клієнтом: " + selected.getFirstName() + " " + selected.getLastName() + " ===");
                    boolean clientMenu = true;
                    while (clientMenu) {
                        System.out.println("1. Переглянути баланс");
                        System.out.println("2. Покласти гроші (Deposit)");
                        System.out.println("3. Зняти гроші (Withdraw)");
                        System.out.println("4. Назад до головного меню");
                        System.out.print("Ваш вибір: ");
                        int op = sc.nextInt();

                        Account acc = selected.getAccount(0); // Перший рахунок
                        switch (op) {
                            case 1 -> System.out.printf("Баланс: %.2f грн\n", acc.getBalance());
                            case 2 -> {
                                System.out.print("Введіть суму для зарахування: ");
                                double dep = sc.nextDouble();
                                acc.deposit(dep);
                            }
                            case 3 -> {
                                System.out.print("Введіть суму для зняття: ");
                                double w = sc.nextDouble();
                                acc.withdraw(w);
                            }
                            case 4 -> clientMenu = false;
                            default -> System.out.println("❌ Невірний вибір.");
                        }
                    }
                }
                case 3 -> {
                    running = false;
                    System.out.println("👋 До побачення!");
                }
                default -> System.out.println("❌ Невірний вибір!");
            }
        }
    }
}

