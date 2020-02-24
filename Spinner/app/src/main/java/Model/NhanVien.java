package Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private String name;
    private String thuBatDauCongTac;
    private int soNgayCT;

    public NhanVien() {
    }

    public NhanVien(String name, String thuBatDauCongTac, int soNgayCT) {
        this.name = name;
        this.thuBatDauCongTac = thuBatDauCongTac;
        this.soNgayCT = soNgayCT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThuBatDauCongTac() {
        return thuBatDauCongTac;
    }

    public void setThuBatDauCongTac(String thuBatDauCongTac) {
        this.thuBatDauCongTac = thuBatDauCongTac;
    }

    public int getSoNgayCT() {
        return soNgayCT;
    }

    public void setSoNgayCT(int soNgayCT) {
        this.soNgayCT = soNgayCT;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name + "\n Bat dau di cong tac vao thu [" + this.thuBatDauCongTac + "]" +
                "\n So ngay cong tac du kien: " + this.soNgayCT;
    }
}
