package pe.com.example.bikerental.thirdparty.mongodb;

import java.io.Serializable;


public class BikeVo implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private String bikeId;

  private int quantity;

  public String getBikeId() {
    return this.bikeId;
  }

  public void setBikeId(String bikeId) {
    this.bikeId = bikeId;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}