package pe.com.example.bikerental.thirdparty.mssql;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
//import pe.com.example.bikerental.thirdparty.redis.HistoryStatus;

@Entity
@Table(name = "Bike", schema = "BCP")
public class BikeDto implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -4357742218022307678L;

  @Id
  @Column(name = "bike_id", columnDefinition = "")
  private String bikeId;

  @Column(name = "type")
  private String type;

  @Column(name = "brand")
  private String brand;

  @Column(name = "price_by_minute")
  private String priceByMinute;

  @Column(name = "is_active")
  private String isActive;

  public String getBikeId() {
    return bikeId;
  }

  public void setBikeId(String bikeId) {
    this.bikeId = bikeId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getPriceByMinute() {
    return priceByMinute;
  }

  public void setPriceByMinute(String priceByMinute) {
    this.priceByMinute = priceByMinute;
  }

  public String getIsActive() {
    return isActive;
  }

  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }
}