/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jexer;

/**
 *
 * @author user
 */
public class TMenu {
    private String name;
    public TMenu(String name) {
        this.name = name;
    }
    public void addItem(int id, String itemName) {
        System.out.println("Додано пункт меню [" + id + "]: " + itemName);
    }
    public void showMenu() {
        System.out.println("Меню \"" + name + "\" показано.");
    }
}
