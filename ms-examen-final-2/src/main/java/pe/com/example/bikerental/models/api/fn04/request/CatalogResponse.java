package pe.com.example.bikerental.models.api.fn04.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.List;

@JsonInclude(value = Include.NON_NULL)
public class CatalogResponse implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1085750636767106106L;

  private String stationId;
  private String name;
  private String location;
  private String isActive;
  private List<Bike> listBike;

  public CatalogResponse(String stationId, String name, String location, String isActive, List<Bike> listBike) {
    this.stationId = stationId;
    this.name = name;
    this.location = location;
    this.isActive = isActive;
    this.listBike = listBike;
  }

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

  public List<Bike> getListBike() {
    return listBike;
  }

  public void setListBike(List<Bike> listBike) {
    this.listBike = listBike;
  }
}