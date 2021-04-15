/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLyHSTCP;

/**
 *
 * @author er1nzz
 */
public class HS {
    private String MaHS;
    private String Ten;
    private String DiaChi;
    private String GioiTinh ;
    private float DiemTongket;

    public HS() {
    }

    @Override
    public String toString() {
        return "HS{" + "MaHS=" + MaHS + ", Ten=" + Ten + ", DiaChi=" + DiaChi + ", GioiTinh=" + GioiTinh + ", DiemTongket=" + DiemTongket + '}';
    }
    
    public String toWrite() {
        return MaHS + "$" + Ten + "$" + DiaChi + "$" + GioiTinh + "$" + DiemTongket;
    }

    public HS(String MaHS, String Ten, String DiaChi, String GioiTinh, float DiemTongket) {
        this.MaHS = MaHS;
        this.Ten = Ten;
        this.DiaChi = DiaChi;
        this.GioiTinh = GioiTinh;
        this.DiemTongket = DiemTongket;
    }

    public String getMaHS() {
        return MaHS;
    }

    public void setMaHS(String MaHS) {
        this.MaHS = MaHS;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public float getDiemTongket() {
        return DiemTongket;
    }

    public void setDiemTongket(float DiemTongket) {
        this.DiemTongket = DiemTongket;
    }
    
    
            
}
