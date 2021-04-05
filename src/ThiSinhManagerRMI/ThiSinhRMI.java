/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiSinhManagerRMI;

/**
 *
 * @author er1nzz
 */
public class ThiSinhRMI {
    private String name;
    private String ID;
    private String address;
    private String birthyear;
    private double score1;
    private double score2;
    private double score3;

    public ThiSinhRMI() {
    }

    public ThiSinhRMI(String name, String ID, String address, String birthyear, double score1, double score2, double score3) {
        this.name = name;
        this.ID = ID;
        this.address = address;
        this.birthyear = birthyear;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public double getScore1() {
        return score1;
    }

    public void setScore1(double score1) {
        this.score1 = score1;
    }

    public double getScore2() {
        return score2;
    }

    public void setScore2(double score2) {
        this.score2 = score2;
    }

    public double getScore3() {
        return score3;
    }

    public void setScore3(double score3) {
        this.score3 = score3;
    }
    
    public double getTotal(){
        return this.score1+this.score2+this.score3;
    }

    @Override
    public String toString() {
        return "name=" + name + ", ID=" + ID + ", address=" + address + ", birthyear=" + birthyear + ", score1=" + score1 + ", score2=" + score2 + ", score3=" + score3 ;
    }
    
    
    
}
