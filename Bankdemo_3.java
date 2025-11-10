/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bankdemo_3;

/**
 *
 * @author user
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bankdemo_3 {

    static class Account {
        private double balance;

        public Account(double initialBalance) {
            this.balance = initialBalance;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("✅ Зараховано " + amount + " грн.");
            } else {
                System.out.println("❌ Невірна сума!");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("💸 Знято " + amount + " грн.");
            } else {
                System.out.println("❌ Недостатньо коштів або невірна сума!");
            }
        }
    }

    static class Customer {
        private String name;
        private List<Account> accounts = new ArrayList<>();

        public Customer(String name, double balance) {
            this.name = name;
            accounts.add(new Account(balance));
        }

        public String getName() {
            return name;
        }

        public List<Account> getAccounts() {
            return accounts;
        }
    }

    static class Bank {
        private List<Customer> customers = new ArrayList<>();

        public void addCustomer(String name, double balance) {
            customers.add(new Customer(name, balance));
        }

        public List<Customer> getCustomers() {
            return customers;
        }

        public Customer getCustomer(int index) {
            if (index >= 0 && index < customers.size()) {
                return customers.get(index);
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        // Створюємо кількох клієнтів
        bank.addCustomer("Іван Петренко", 1500);
        bank.addCustomer("Олена Коваль", 2500);
        bank.addCustomer("Марія Гнатюк", 800);

        System.out.println("💰 Ласкаво просимо до банківської системи!");
        System.out.println("Введіть 'help' для перегляду команд.\n");

        boolean running = true;
        while (running) {
            System.out.print("bank> ");
            String command = sc.nextLine().trim();

            switch (command) {
                case "help":
                    System.out.println("""
                        📖 Доступні команди:
                        help         - показати це меню
                        list         - показати список клієнтів
                        info <id>    - показати інформацію про клієнта (0,1,2...)
                        deposit <id> <сума>  - зарахувати кошти на рахунок клієнта
                        withdraw <id> <сума> - зняти кошти з рахунку клієнта
                        report       - показати звіт про всіх клієнтів
                        exit         - вийти з програми
                        """);
                    break;

                case "list":
                    System.out.println("👥 Список клієнтів:");
                    int i = 0;
                    for (Customer c : bank.getCustomers()) {
                        System.out.println(i + ": " + c.getName());
                        i++;
                    }
                    break;

                case "report":
                    System.out.println("📊 Звіт по клієнтах банку:");
                    for (Customer c : bank.getCustomers()) {
                        System.out.println("- " + c.getName() + ": "
                                + c.getAccounts().get(0).getBalance() + " грн");
                    }
                    break;

                default:
                    if (command.startsWith("info")) {
                        try {
                            int id = Integer.parseInt(command.split(" ")[1]);
                            Customer c = bank.getCustomer(id);
                            if (c != null) {
                                System.out.println("👤 Ім'я: " + c.getName());
                                System.out.println("💰 Баланс: " + c.getAccounts().get(0).getBalance() + " грн");
                            } else {
                                System.out.println("❌ Клієнта з таким ID не знайдено!");
                            }
                        } catch (Exception e) {
                            System.out.println("❌ Використання: info <id>");
                        }
                    } else if (command.startsWith("deposit")) {
                        try {
                            String[] parts = command.split(" ");
                            int id = Integer.parseInt(parts[1]);
                            double amount = Double.parseDouble(parts[2]);
                            Customer c = bank.getCustomer(id);
                            if (c != null) {
                                c.getAccounts().get(0).deposit(amount);
                            } else {
                                System.out.println("❌ Клієнта не знайдено!");
                            }
                        } catch (Exception e) {
                            System.out.println("❌ Використання: deposit <id> <сума>");
                        }
                    } else if (command.startsWith("withdraw")) {
                        try {
                            String[] parts = command.split(" ");
                            int id = Integer.parseInt(parts[1]);
                            double amount = Double.parseDouble(parts[2]);
                            Customer c = bank.getCustomer(id);
                            if (c != null) {
                                c.getAccounts().get(0).withdraw(amount);
                            } else {
                                System.out.println("❌ Клієнта не знайдено!");
                            }
                        } catch (Exception e) {
                            System.out.println("❌ Використання: withdraw <id> <сума>");
                        }
                    } else if (command.equals("exit")) {
                        running = false;
                        System.out.println("👋 Дякуємо за використання банківської системи!");
                    } else {
                        System.out.println("❓ Невідома команда! Введіть 'help' для довідки.");
                    }
                    break;
            }
        }
    }
}
