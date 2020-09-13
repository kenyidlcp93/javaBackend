package pe.com.example.bikerental.repository.mssql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.example.bikerental.models.api.fn04.request.Bike;
import pe.com.example.bikerental.thirdparty.mssql.BikeDto;
import pe.com.example.bikerental.thirdparty.mssql.BookingDto;
import pe.com.example.bikerental.thirdparty.mssql.DetailStationsDto;

import java.util.List;

@Repository
public interface DetailStationRepository extends JpaRepository<DetailStationsDto, String> {

  @Query("SELECT b.bikeId, b.type, b.brand, b.priceByMinute, b.isActive FROM DetailStationsDto d join BikeDto b on b.bikeId = d.bikeId WHERE d.stationId = ?1")
  List<BikeDto> findBikebyStationId(String stationId);
}