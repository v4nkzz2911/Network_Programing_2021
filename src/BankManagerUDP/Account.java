/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankManagerUDP;

/**
 *
 * @author er1nzz
 */
public class Account {
    private String name;
    private String username;
    private String pass;
    private int balance;

    public Account(String name, String username, String pass, int balance) {
        this.name = name;
        this.username = username;
        this.pass = pass;
        this.balance = balance;
    }

    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" + "name=" + name + ", username=" + username + ", pass=" + pass + ", balance=" + balance + '}';
    }
    
    
    
}
