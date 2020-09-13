package pe.com.example.bikerental.repository.mssql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.example.bikerental.thirdparty.mssql.BookingDto;

@Repository
public interface BikeRentalRepository extends JpaRepository<BookingDto, Integer> {

}