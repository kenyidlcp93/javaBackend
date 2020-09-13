package pe.com.example.bikerental.models.api.fn03.request;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class RentalStation implements Serializable {

  private static final long serialVersionUID = -6843792667863997393L;
  @NotNull
  @Valid
  private Station station;

  public Station getStation() {
    return this.station;
  }

  public void setStation(Station station) {
    this.station = station;
  }
}
