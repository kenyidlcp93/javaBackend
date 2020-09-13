package pe.com.example.bikerental.constants;

public final class QueryStatement {

  public static final String QUERY_INSERT_BOOKING =
      "INSERT INTO BOOKING(is_canceled, created_at, user_id, bike_id) VALUES(false, ?, ?, ?)";

  public static final String QUERY_INSERT_RENTAL_DETAILS =
      "INSERT INTO RENTALDETAILS(booking_id, origin_station_id, destination_station_id, start_date, end_date) "
      + "VALUES((SELECT BOOKING_ID FROM BOOKING WHERE CREATED_AT = ? AND USER_ID = ? AND is_canceled = false), ?, ?, ?, ?)";

}
