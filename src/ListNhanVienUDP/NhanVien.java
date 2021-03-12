/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListNhanVienUDP;

/**
 *
 * @author er1nzz
 */
public class NhanVien {
    private String name;
    private int age;
    private int salary;

    public NhanVien() {
    }

    public NhanVien(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + "\t" + age + "\t" + salary ;
    }
    
    
    
}
