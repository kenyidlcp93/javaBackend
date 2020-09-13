package pe.com.example.bikerental.business.fn01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pe.com.example.bikerental.models.api.fn03.request.BikeRentalResponse;
import pe.com.example.bikerental.models.api.fn03.request.RentalBikeRequest;
import pe.com.example.bikerental.repository.mssql.BikeRentalRepository;
import pe.com.example.bikerental.repository.mssql.BookingDetailRepository;
import pe.com.example.bikerental.thirdparty.mssql.BookingDetailDto;
import pe.com.example.bikerental.thirdparty.mssql.BookingDto;
//import pe.com.example.bikerental.thirdparty.redis.HistoryStatus;
//import pe.com.example.bikerental.thirdparty.redis.InteractionDto;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.function.Function;

import static pe.com.example.utils.Utils.getDatetimeSystem;

@Component
public class BookingCreationSender {

  private static final Logger log = LoggerFactory.getLogger(BookingCreationSender.class);

  private BikeRentalRepository rentalRepository;
  private BookingDetailRepository bookingDetailRepository;

  public BookingCreationSender(BikeRentalRepository rentalRepository,
                               BookingDetailRepository bookingDetailRepository) {
    this.rentalRepository = rentalRepository;
    this.bookingDetailRepository = bookingDetailRepository;
  }

  /**
   * method for create a new booking.
   *
   * @param payload request
   * @throws SQLException exception
   */
  public Function<RentalBikeRequest, Mono<BikeRentalResponse>> createBookingAndDetails() {
    return (payload) -> {
      return processCreation().apply(payload)
          .flatMap(parseToResponse)
          .doOnSuccess((success) -> {log.info("[creation bike detail] successful!");})
          .doOnError((err) -> log.error("[creation bike rent] Error {}", err));
    };

  }

  /**
   * método que realiza el proceso de parsear el request al dto y persistir en AzureSQL.
   */
  private Function<RentalBikeRequest, Mono<BookingDto>> processCreation() {
    return (payload) -> {
      BookingDto dto = new BookingDto();
      dto.setCreatedAt(getDatetimeSystem.get());
      dto.setUserId(payload.getUserId());
      dto.setBikeId(payload.getBike().getCode());

      BookingDetailDto dtoDetail = new BookingDetailDto();
      dtoDetail.setBookingId(rentalRepository.save(dto).getBookingId());
      dtoDetail.setOriginStationId(payload.getOrigin().getStation().getCode());
      dtoDetail.setDestinationStationId(payload.getDestination().getStation().getCode());
      dtoDetail.setStartDate(getDatetimeSystem.get());
      dtoDetail.setEndDate(getDatetimeSystem.get());
      bookingDetailRepository.save(dtoDetail);

      return Mono.just(dto);
    };
  }

  private Function<BookingDto, Mono<BikeRentalResponse>> parseToResponse = (interaction) -> {
    return Mono.just(new BikeRentalResponse(interaction.getBookingId()));
  };

}
