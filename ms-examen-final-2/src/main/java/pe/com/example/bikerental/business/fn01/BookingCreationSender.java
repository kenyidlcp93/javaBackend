package pe.com.example.bikerental.business.fn01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pe.com.example.bikerental.business.fn11.BikeInventorySender;
import pe.com.example.bikerental.models.api.fn03.request.BikeRentalResponse;
import pe.com.example.bikerental.models.api.fn03.request.RentalBikeRequest;
import pe.com.example.bikerental.repository.mssql.BikeRentalRepository;
import pe.com.example.bikerental.repository.mssql.BookingDetailRepository;
import pe.com.example.bikerental.thirdparty.mongodb.InventoryStatus;
import pe.com.example.bikerental.thirdparty.mssql.BookingDetailDto;
import pe.com.example.bikerental.thirdparty.mssql.BookingDto;
import reactor.core.publisher.Mono;


import java.time.LocalDateTime;
import java.util.function.Function;

import static pe.com.example.utils.Utils.getDatetimeSystem;

@Component
public class BookingCreationSender {

  private static final Logger log = LoggerFactory.getLogger(BookingCreationSender.class);

  private BikeRentalRepository rentalRepository;
  private BookingDetailRepository bookingDetailRepository;
  private final BikeInventorySender inventorySender;

  public BookingCreationSender(BikeRentalRepository rentalRepository,
                               BookingDetailRepository bookingDetailRepository,
                               BikeInventorySender inventorySender) {
    this.rentalRepository = rentalRepository;
    this.bookingDetailRepository = bookingDetailRepository;
    this.inventorySender = inventorySender;
  }

  /**
   * method for create a new booking.
   */
  public Mono<BikeRentalResponse> createBookingAndDetails(RentalBikeRequest payload) {
      return processCreation(payload)
              .flatMap(dtoSaved -> processCreationDetails(dtoSaved, payload))
              .flatMap(detail -> inventorySender.updateInventory(detail, InventoryStatus.DECREMENT))
              .flatMap(res -> Mono.just(new BikeRentalResponse(res.getBookingId())));


  }

  /**
   * método que realiza el proceso de parsear el request al dto y persistir en AzureSQL.
   */
  private Mono<BookingDto> processCreation(RentalBikeRequest payload) {

    return Mono.defer(() -> {
      BookingDto booking = new BookingDto();
      booking.setBikeId(payload.getBike().getCode());
      booking.setUserId(payload.getUserId());
      booking.setCreatedAt(getDatetimeSystem.get());

      return Mono.justOrEmpty(rentalRepository.save(booking));
    });
  }

  private Mono<BookingDetailDto> processCreationDetails(BookingDto dtoSaved, RentalBikeRequest payload) {

    return Mono.defer(() -> {
      BookingDetailDto dtoDetail = new BookingDetailDto();

      dtoDetail.setBookingId(rentalRepository.save(dtoSaved).getBookingId());
      dtoDetail.setOriginStationId(payload.getOrigin().getStation().getCode());
      dtoDetail.setDestinationStationId(payload.getDestination().getStation().getCode());
      dtoDetail.setStartDate(LocalDateTime.parse(payload.getStartDate()));


      return Mono.justOrEmpty(bookingDetailRepository.save(dtoDetail));
    });
  }

  private Function<BookingDto, Mono<BikeRentalResponse>> parseToResponse = (interaction) -> {
    return Mono.just(new BikeRentalResponse(interaction.getBookingId()));
  };

}
