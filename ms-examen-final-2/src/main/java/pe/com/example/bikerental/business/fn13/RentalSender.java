package pe.com.example.bikerental.business.fn13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pe.com.example.bikerental.repository.mssql.BikeRentalRepository;
import pe.com.example.bikerental.thirdparty.mssql.BookingDto;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * BikeRentalSender
 */
@Component
public final class RentalSender {

  private static final Logger log = LoggerFactory.getLogger(RentalSender.class);

  private final BikeRentalRepository bikeRentalRepository;

  public RentalSender(BikeRentalRepository bikeRentalRepository) {
      this.bikeRentalRepository = bikeRentalRepository;
  }


  /**
   * método que nos permite buscar un alquiler en AzureSQL.
   *
   * @param bookingId
   * @return Mono
   */
  public Mono<BookingDto> findBookingById(Integer bookingId) {
    return Mono.defer(() -> {
          log.info("[by find bookingId] {}", bookingId);
          if (bookingId < 0) {
            return Mono.error(new Exception("[find booking] BookingId not valid"));
          }
          return Mono.fromCallable(() -> bikeRentalRepository.findById(bookingId).orElse(new BookingDto()));
        })
        .subscribeOn(Schedulers.elastic())
        .doOnSuccess((dto) -> log.info("[Search booking] found {}", dto.getBookingId()))
        .doOnError((tw) -> log.error("[Search booking] error => {}", tw.getMessage()));
  }

}
