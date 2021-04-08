/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachSanUDP;

/**
 *
 * @author er1nzz
 */
public class PhongKhachSan {
    private String ID;
    private String type;
    private String address;
    private String status;

    public PhongKhachSan() {
    }

    public PhongKhachSan(String ID, String type, String address, String status) {
        this.ID = ID;
        this.type = type;
        this.address = address;
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return  ID + "\t" + type + "\t\t" + address + "\t\t" + status ;
    }
    
    
    
}
