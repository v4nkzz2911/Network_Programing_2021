/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VX;

/**
 *
 * @author er1nzz
 */
public class user {
    private String name;
    private String m1;
    private String m2;

    public user(String name, String m1, String m2) {
        this.name = name;
        this.m1 = m1;
        this.m2 = m2;
    }

    public user() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getM1() {
        return m1;
    }

    public void setM1(String m1) {
        this.m1 = m1;
    }

    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
        this.m2 = m2;
    }

    @Override
    public String toString() {
        return "user{" + "name=" + name + ", m1=" + m1 + ", m2=" + m2 + '}';
    }
    
    
    
}
