package pe.com.example.bikerental.business.fn13;

import pe.com.example.bikerental.thirdparty.mssql.BookingDto;
import reactor.core.publisher.Mono;

public interface RentalService {

  Mono<BookingDto> getBikeRentalByBikeRentId(Integer rentId);

}
