/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tuidemo;

/**
 *
 * @author user
 */
public class Bank {
    private Customer[] customers;

    public Bank() {
        customers = new Customer[2];
        customers[0] = new Customer("Іван Іваненко", 1500.0);
        customers[1] = new Customer("Олена Петренко", 2300.0);
    }

    public Customer getCustomer(int index) {
        if (index >= 0 && index < customers.length) {
            return customers[index];
        } else {
            return null;
        }
    }

    public int getNumberOfCustomers() {
        return customers.length;
    }
}
