package pe.com.example.bikerental.repository.mssql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.example.bikerental.thirdparty.mssql.DetailStationsDto;
import pe.com.example.bikerental.thirdparty.mssql.StationDto;

@Repository
public interface StationDtoRepository extends JpaRepository<StationDto, String> {

}