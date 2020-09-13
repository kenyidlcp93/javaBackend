package pe.com.example.bikerental.models.api.fn03.request;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class BookingDetail implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -4357742218022307678L;

  private int bookingId;

  private String originStationId;

  private String destinationStationId;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  public BookingDetail(int bookingId, String originStationId, String destinationStationId, LocalDateTime startDate, LocalDateTime endDate) {
    this.bookingId = bookingId;
    this.originStationId = originStationId;
    this.destinationStationId = destinationStationId;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public int getBookingId() {
    return bookingId;
  }

  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

  public String getOriginStationId() {
    return originStationId;
  }

  public void setOriginStationId(String originStationId) {
    this.originStationId = originStationId;
  }

  public String getDestinationStationId() {
    return destinationStationId;
  }

  public void setDestinationStationId(String destinationStationId) {
    this.destinationStationId = destinationStationId;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }
}