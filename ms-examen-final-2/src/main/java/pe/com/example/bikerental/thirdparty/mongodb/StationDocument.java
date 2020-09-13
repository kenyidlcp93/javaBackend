package pe.com.example.bikerental.thirdparty.mongodb;

import java.io.Serializable;
import java.util.Collection;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StationDocument implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  private String id;

  @Indexed
  private String stationId;


  private Collection<BikeVo> bikes;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStationId() {
    return this.stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  public Collection<BikeVo> getBikes() {
    return this.bikes;
  }

  public void setBikes(Collection<BikeVo> bikes) {
    this.bikes = bikes;
  }

}