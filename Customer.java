/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tuidemo;

/**
 *
 * @author user
 */
public class Customer {
    private String name;
    private Account account;

    public Customer(String name, double initialBalance) {
        this.name = name;
        this.account = new Account(initialBalance);
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }
}


