package pe.com.example.bikerental.thirdparty.mssql;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
//import pe.com.example.bikerental.thirdparty.redis.HistoryStatus;

@Entity
@Table(name = "Detail_Stations", schema = "BCP")
public class DetailStationsDto implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -4357742218022307678L;

  @Id
  @Column(name = "station_id", columnDefinition = "")
  private String stationId;

  @Column(name = "bike_id")
  private String bikeId;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "is_active")
  private String isActive;

  public String getStationId() {
    return stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  public String getBikeId() {
    return bikeId;
  }

  public void setBikeId(String bikeId) {
    this.bikeId = bikeId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getIsActive() {
    return isActive;
  }

  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }
}