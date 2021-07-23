/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HayChonGiaDungRMI_23_7;

/**
 *
 * @author er1nzz
 */
public class SanPham {
    private String ID;
    private String name;
    private int price;

    public SanPham() {
    }

    public SanPham(String ID, String name, int price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SanPham{" + "ID=" + ID + ", name=" + name + ", price=" + price + '}';
    }
    
    
}
