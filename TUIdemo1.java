/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tuidemo1;

/**
 *
 * @author user
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// ---------------- ІМІТАЦІЯ Jexer ----------------
class TApplication implements Runnable {
    public enum BackendType { SWING }
    public TApplication(BackendType backend) {}
    @Override public void run() {}
    public void messageBox(String title, String msg) {
        System.out.println("\n[" + title + "] " + msg);
    }
}

class TAction { public void DO() {} }

class TWindow {
    private String title;
    public TWindow(String title) { this.title = title; }
    public void newStatusBar(String text) {
        System.out.println("[" + title + "] " + text);
    }
}

class TMenuEvent {
    private int id;
    public TMenuEvent(int id) { this.id = id; }
    public int getId() { return id; }
}
// --------------------------------------------------


// ---------------- КЛАСИ БАНКУ ----------------
class Account {
    private double balance;
    private String type;

    public Account(double balance, String type) {
        this.balance = balance;
        this.type = type;
    }

    public double getBalance() { return balance; }
    public String getType() { return type; }
}

class Customer {
    private String name;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name) { this.name = name; }

    public void addAccount(Account acc) {
        accounts.add(acc);
    }

    public String getName() { return name; }

    public Account getAccount(int index) {
        if (index >= 0 && index < accounts.size()) return accounts.get(index);
        return null;
    }
}

class Bank {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public Customer getCustomer(int index) {
        if (index >= 0 && index < customers.size()) return customers.get(index);
        return null;
    }

    public int getNumOfCustomers() {
        return customers.size();
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
// --------------------------------------------------


// ---------------- ГОЛОВНИЙ КЛАС ----------------
public class TUIdemo1 extends TApplication {

    private static final int ABOUT_APP = 2000;
    private static final int CUST_INFO = 2010;

    private static Bank bank = new Bank();

    public static void main(String[] args) throws Exception {
        TUIdemo1 app = new TUIdemo1();
        app.run();
    }

    public TUIdemo1() throws Exception {
        super(BackendType.SWING);
        System.out.println("=== Simple Bank TUI Demo ===");

        // “На 4” — створення клієнтів у коді
        initializeBank();

        // “На 5” — якщо є test.dat, завантажуємо з файлу
        loadFromFile();

        // Запуск імітації вікна
        ShowCustomerDetails();
    }

    private void initializeBank() {
        Customer c1 = new Customer("John Doe");
        c1.addAccount(new Account(200.00, "Checking"));

        Customer c2 = new Customer("Jane Smith");
        c2.addAccount(new Account(500.00, "Savings"));

        bank.addCustomer(c1);
        bank.addCustomer(c2);
    }

    private void loadFromFile() {
        File f = new File("test.dat");
        if (!f.exists()) {
            System.out.println("(Файл test.dat не знайдено — використовуються дані за замовчуванням)");
            return;
        }

        try (Scanner sc = new Scanner(f)) {
            bank = new Bank(); // перезаписуємо базу клієнтів
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 3) continue;
                String name = parts[0];
                double bal = Double.parseDouble(parts[1]);
                String type = parts[2];
                Customer c = new Customer(name);
                c.addAccount(new Account(bal, type));
                bank.addCustomer(c);
            }
            System.out.println("✅ Дані банку завантажено з файлу test.dat");
        } catch (FileNotFoundException e) {
            System.out.println("Помилка читання test.dat: " + e.getMessage());
        }
    }

    private void ShowCustomerDetails() {
        TWindow custWin = new TWindow("Customer Window");
        custWin.newStatusBar("Введіть номер клієнта та натисніть Show...");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nВведіть номер клієнта (0.." + (bank.getNumOfCustomers() - 1) + ") або 'exit' для виходу:");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Вихід із програми...");
                break;
            }

            try {
                int custNum = Integer.parseInt(input);
                if (custNum < 0 || custNum >= bank.getNumOfCustomers()) {
                    messageBox("Error", "❌ Клієнта з таким номером не існує!");
                    continue;
                }

                Customer c = bank.getCustomer(custNum);
                Account a = c.getAccount(0);
                String details = "Owner Name: " + c.getName() +
                        "\nAccount Type: " + a.getType() +
                        "\nAccount Balance: $" + a.getBalance();
                messageBox("Customer Info", details);
            } catch (NumberFormatException e) {
                messageBox("Error", "Введіть ціле число або 'exit'.");
            }
        }
    }
}

