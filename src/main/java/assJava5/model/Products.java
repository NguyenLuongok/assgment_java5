package assJava5.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Products {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotNull
  private String maSP;
  @NotNull
  private String tenSP;
  @NotNull
  private double giaSP;
  @NotNull
  private String hinhAnh;
  @NotNull
  private String ghiChu;

  public Products(){}
  public Products(String maSP, String tenSP, double giaSP,String hinhAnh, String ghiChu){
      this.maSP=maSP;
      this.tenSP=tenSP;
      this.giaSP=giaSP;
      this.hinhAnh=hinhAnh;
      this.ghiChu=ghiChu;
  }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", maSP='" + maSP + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", giaSP=" + giaSP +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(double giaSP) {
        this.giaSP = giaSP;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
