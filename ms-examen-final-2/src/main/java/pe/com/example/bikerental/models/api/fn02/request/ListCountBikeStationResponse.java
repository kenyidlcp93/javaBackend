package pe.com.example.bikerental.models.api.fn02.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ListCountBikeStationResponse implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -4357742218022307678L;

  private List<CountBikeStation> countBikeStationList;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public List<CountBikeStation> getCountBikeStationList() {
    return countBikeStationList;
  }

  public void setCountBikeStationList(List<CountBikeStation> countBikeStationList) {
    this.countBikeStationList = countBikeStationList;
  }
}