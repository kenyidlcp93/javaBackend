package pe.com.example.bikerental.thirdparty.mssql;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import pe.com.example.bikerental.thirdparty.redis.HistoryStatus;

@Entity
@Table(name = "Booking", schema = "BCP")
public class BookingDto implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -4357742218022307678L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "booking_id", columnDefinition = "")
  private int bookingId;

  @Column(name = "is_canceled")
  private boolean canceled;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "bike_id")
  private String bikeId;

  @Column(name = "date_completed")
  private LocalDateTime dateCompleted;

  @Column(name = "is_completed")
  private boolean completed;

  //private transient HistoryStatus historyStatus;

  /**
   * @return the bookingId
   */
  public int getBookingId() {
    return bookingId;
  }

  /**
   * @return the historyStatus

  public HistoryStatus getHistoryStatus() {
    return historyStatus;
  }*/

  /**
   * @param historyStatus the historyStatus to set

  public void setHistoryStatus(HistoryStatus historyStatus) {
    this.historyStatus = historyStatus;
  }*/

  /**
   * @return the completed
   */
  public boolean isCompleted() {
    return completed;
  }

  /**
   * @param completed the completed to set
   */
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  /**
   * @return the dateCompleted
   */
  public LocalDateTime getDateCompleted() {
    return dateCompleted;
  }

  /**
   * @param dateCompleted the dateCompleted to set
   */
  public void setDateCompleted(LocalDateTime dateCompleted) {
    this.dateCompleted = dateCompleted;
  }

  /**
   * @return the bikeId
   */
  public String getBikeId() {
    return bikeId;
  }

  /**
   * @param bikeId the bikeId to set
   */
  public void setBikeId(String bikeId) {
    this.bikeId = bikeId;
  }

  /**
   * @return the userId
   */
  public String getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * @return the createdAt
   */
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  /**
   * @param createdAt the createdAt to set
   */
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * @return the canceled
   */
  public boolean isCanceled() {
    return canceled;
  }

  /**
   * @param canceled the canceled to set
   */
  public void setCanceled(boolean canceled) {
    this.canceled = canceled;
  }

  /**
   * @param bookingId the bookingId to set
   */
  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

}