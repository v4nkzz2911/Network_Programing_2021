/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChungCuUDP;

/**
 *
 * @author er1nzz
 */
public class ChungCu {
    private String ID;
    private String price;
    private String status;

    public ChungCu() {
    }

    public ChungCu(String ID, String price, String status) {
        this.ID = ID;
        this.price = price;
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChungCu{" + "ID=" + ID + ", price=" + price + ", status=" + status + '}';
    }
    
    public String toSend(){
        return ID + "$" + price + "$" + status;
    }
}
