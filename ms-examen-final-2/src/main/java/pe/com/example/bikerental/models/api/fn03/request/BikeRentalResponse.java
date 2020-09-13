package pe.com.example.bikerental.models.api.fn03.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

@JsonInclude(value = Include.NON_NULL)
public class BikeRentalResponse implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1085750636767106106L;

  private int rentalId;

  /**
   * @return the rentalId
   */
  public int getRentalId() {
    return rentalId;
  }

  /**
   * @param rentalId the rentalId to set
   */
  public void setRentalId(int rentalId) {
    this.rentalId = rentalId;
  }

  /**
   * @param rentalId
   */
  public BikeRentalResponse(int rentalId) {
    this.rentalId = rentalId;
  }

}