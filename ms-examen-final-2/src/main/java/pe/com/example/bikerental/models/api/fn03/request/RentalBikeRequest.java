package pe.com.example.bikerental.models.api.fn03.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalBikeRequest implements Serializable {
  private static final long serialVersionUID = 1916808489690810192L;


  @NotEmpty
  @NotNull
  @Size(max = 10)
  private String startDate;


  @Valid
  private RentalStation origin;


  @Valid
  private RentalStation destination;

  @Valid
  private RentalBike bike;

  @NotEmpty
  @NotNull
  @Size(max = 5)
  private String userId;

  public String getStartDate() {
    return this.startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public RentalStation getOrigin() {
    return this.origin;
  }

  public void setOrigin(RentalStation origin) {
    this.origin = origin;
  }

  public RentalStation getDestination() {
    return this.destination;
  }

  public void setDestination(RentalStation destination) {
    this.destination = destination;
  }

  public RentalBike getBike() {
    return this.bike;
  }

  public void setBike(RentalBike bike) {
    this.bike = bike;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

}
