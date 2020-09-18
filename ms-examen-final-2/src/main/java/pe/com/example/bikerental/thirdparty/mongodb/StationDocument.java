package pe.com.example.bikerental.thirdparty.mongodb;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mycollection")
public class StationDocument implements Serializable {

  @Id
  private String id;

  private String stationId;

  private String avaiable;


  public String getStationId() {
    return this.stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }

  public String getAvaiable() {
    return this.avaiable;
  }

  public void setAvaiable(String avaiable) {
    this.avaiable = avaiable;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}