package pe.com.example.bikerental.models.api.fn04.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Bike implements Serializable {

  private static final long serialVersionUID = 1143702868657282272L;

  private String bikeId;
  private String type;
  private String brand;
  private String priceByMinute;
  private String isActive;

  public Bike(String bikeId, String type, String brand, String priceByMinute, String isActive) {
    this.bikeId = bikeId;
    this.type = type;
    this.brand = brand;
    this.priceByMinute = priceByMinute;
    this.isActive = isActive;
  }

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
