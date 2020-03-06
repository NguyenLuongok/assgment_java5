package assJava5.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String tenKH;
    private String tenSP;
    private String giaSP;
    private String soLuong;
    private String tongTien;
    @NotNull
    private String diaChi;
    @NotNull
    private String soDT;

    public Bill() {
    }

    public Bill(String tenSP, String giaSP, String soLuong, String tongTien, String diaChi, String soDT) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.diaChi = diaChi;
        this.soDT = soDT;
    }

    public Bill(String tenKH, String diaChi, String soDT ,String tenSP, String giaSP, String soLuong,String tongTien) {
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
        this.tongTien= tongTien;
    }

    @Override
    public String toString(){
        return "Bill{"+"id="+id+",tenKH='"+tenKH+'\''+",tenSP='"+tenSP+'\'' +",giaSP='"+giaSP+'\''+
                ",soLuong='"+soLuong+'\''+ ",tongTien='"+tongTien+'\''+",diaChi='"+diaChi+'\''+
                ",soDT='"+soDT+'\''+ '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(String giaSP) {
        this.giaSP = giaSP;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }
}
