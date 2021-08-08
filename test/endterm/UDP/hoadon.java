/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endterm.UDP;

/**
 *
 * @author er1nzz
 */
public class hoadon {
    private int chiso;
    private double tieuthu;

    public hoadon() {
    }

    public hoadon(int chiso, double tieuthu) {
        this.chiso = chiso;
        this.tieuthu = tieuthu;
    }

    public int getChiso() {
        return chiso;
    }

    public void setChiso(int chiso) {
        this.chiso = chiso;
    }

    public double getTieuthu() {
        return tieuthu;
    }

    public void setTieuthu(double tieuthu) {
        this.tieuthu = tieuthu;
    }

    @Override
    public String toString() {
        return chiso + "\t" + tieuthu;
    }
    
    
}
