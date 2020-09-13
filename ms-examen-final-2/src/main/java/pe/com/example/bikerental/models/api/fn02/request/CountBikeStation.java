package pe.com.example.bikerental.models.api.fn02.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CountBikeStation implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -4357742218022307678L;

  private String station;
  private int available;

  public CountBikeStation(String station, int available) {
    this.station = station;
    this.available = available;
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

  public int getAvailable() {
    return available;
  }

  public void setAvailable(int available) {
    this.available = available;
  }
}