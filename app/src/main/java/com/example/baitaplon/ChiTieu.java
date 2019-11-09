package com.example.baitaplon;

import org.w3c.dom.Text;

import java.util.Date;

public class ChiTieu {
    private int ma;

    public ChiTieu(int ma, String ten, Date ngay, String sotien, String noidung) {
        this.ma = ma;
        this.ten = ten;
        this.ngay = ngay;
        this.sotien = sotien;
        this.noidung = noidung;
    }

    public ChiTieu() {
    }

    public int getMa() {
        return ma;
    }
    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    private String ten;
    private Date ngay;
    private String sotien;
    private String noidung;
}
