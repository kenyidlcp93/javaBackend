package pe.com.example.bikerental.repository.mssql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.example.bikerental.thirdparty.mssql.BikeDto;
import pe.com.example.bikerental.thirdparty.mssql.DetailStationsDto;

@Repository
public interface BikeRepository extends JpaRepository<BikeDto, String> {

}