/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jexer;

/**
 *
 * @author user
 */
public class Main {
    public static void main(String[] args) {
        TApplication app = new TApplication();
        app.run();

        TMenu menu = new TMenu("Головне меню");
        menu.addItem(1, "About");
        menu.addItem(2, "Exit");
        menu.showMenu();
    }
}

