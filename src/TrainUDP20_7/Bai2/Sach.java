/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainUDP20_7.Bai2;

/**
 *
 * @author er1nzz
 */
public class Sach {
    private String ID;
    private String name;
    private String publisher;
    private int total;
    private int rent;

    public Sach() {
    }

    public Sach(String ID, String name, String publisher, int total, int rent) {
        this.ID = ID;
        this.name = name;
        this.publisher = publisher;
        this.total = total;
        this.rent = rent;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    @Override
    public String toString() {
        return ID + "\t" + name + "\t" + publisher + "\t" + total + "\t" + rent + '\n';
    }
    
    
    
}
