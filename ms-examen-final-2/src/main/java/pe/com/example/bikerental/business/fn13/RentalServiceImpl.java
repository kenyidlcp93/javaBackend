package pe.com.example.bikerental.business.fn13;

import org.springframework.stereotype.Service;
import pe.com.example.bikerental.thirdparty.mssql.BookingDto;
import reactor.core.publisher.Mono;

@Service
public final class RentalServiceImpl implements RentalService {

  private final RentalSender sender;

  public RentalServiceImpl(RentalSender sender) {
    this.sender = sender;
  }

  @Override
  public Mono<BookingDto> getBikeRentalByBikeRentId(Integer rentId) {
    return sender.findBookingById(rentId);
  }
}