/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThueNhaRMI;

/**
 *
 * @author er1nzz
 */
public class House {
    private String ID;
    private String kind;
    private String address;
    private String status;

    public House() {
    }

    public House(String ID, String kind, String address, String status) {
        this.ID = ID;
        this.kind = kind;
        this.address = address;
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ID + "\t" + kind + "\t" + address + "\t\t\t" + status ;
    }
    
    
    
}
