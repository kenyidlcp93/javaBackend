package pe.com.example.bikerental.thirdparty.mssql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
//import pe.com.example.bikerental.thirdparty.redis.HistoryStatus;

@Entity
@Table(name = "Station", schema = "BCP")
public class StationDto implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -4357742218022307678L;

  @Id
  @Column(name = "station_id", columnDefinition = "")
  private String stationId;

  @Column(name = "name")
  private String name;

  @Column(name = "location")
  private String location;

  @Column(name = "is_active")
  private String isActive;

  public String getStationId() {
    return stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getIsActive() {
    return isActive;
  }

  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }
}