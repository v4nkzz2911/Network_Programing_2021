/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainTCP22_7.Bai2;

/**
 *
 * @author er1nzz
 */
public class Cauthu {
    private String ID;
    private String name;
    private String bornyear;
    private String pos;
    private int defsalary;

    public Cauthu() {
    }

    public Cauthu(String ID, String name, String bornyear, String pos, int defsalary) {
        this.ID = ID;
        this.name = name;
        this.bornyear = bornyear;
        this.pos = pos;
        this.defsalary = defsalary;
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

    public String getBornyear() {
        return bornyear;
    }

    public void setBornyear(String bornyear) {
        this.bornyear = bornyear;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public int getDefsalary() {
        return defsalary;
    }

    public void setDefsalary(int defsalary) {
        this.defsalary = defsalary;
    }

    @Override
    public String toString() {
        return ID + "\t" + name + "\t" + bornyear + "\t" + pos + "\t" + defsalary +"\n";
    }
    
    
}
