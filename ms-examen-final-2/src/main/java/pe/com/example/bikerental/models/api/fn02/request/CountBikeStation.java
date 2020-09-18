package pe.com.example.bikerental.models.api.fn02.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;


@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CountBikeStation implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -4357742218022307678L;

  private String station;
  private int avaiable;

  public CountBikeStation(String station, int avaiable) {
    this.station = station;
    this.avaiable = avaiable;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getStation() {
    return station;
  }

  public void setStation(String station) {
    this.station = station;
  }

  public int getAvaiable() {
    return avaiable;
  }

  public void setAvaiable(int avaiable) {
    this.avaiable = avaiable;
  }
}