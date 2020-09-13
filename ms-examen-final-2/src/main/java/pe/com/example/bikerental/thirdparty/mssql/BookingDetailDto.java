package pe.com.example.bikerental.thirdparty.mssql;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Booking_details", schema = "BCP")
public class BookingDetailDto implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -4357742218022307678L;

  @Id
  @Column(name = "booking_id", columnDefinition = "")
  private int bookingId;

  @Column(name = "origin_station_id")
  private String originStationId;

  @Column(name = "destination_station_id")
  private String destinationStationId;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

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