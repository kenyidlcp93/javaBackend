package pe.com.example.bikerental.business.fn11;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pe.com.example.bikerental.business.fn13.RentalService;
import pe.com.example.bikerental.repository.mongodb.StationRepository;
import pe.com.example.bikerental.thirdparty.mongodb.BikeVo;
import pe.com.example.bikerental.thirdparty.mongodb.InventoryStatus;
import pe.com.example.bikerental.thirdparty.mongodb.StationDocument;

import pe.com.example.bikerental.thirdparty.mssql.BookingDetailDto;
import reactor.core.publisher.Mono;

@Component
public final class BikeInventorySender {

  private static final Logger log = LoggerFactory.getLogger(BikeInventorySender.class);

  private final StationRepository stationRepository;
  private final RentalService bikeRentalService;
  /**
   * @param stationRepository
   */
  public BikeInventorySender(StationRepository stationRepository, RentalService bikeRentalService) {
    this.stationRepository = stationRepository;
    this.bikeRentalService = bikeRentalService;
  }

  /**
   * método que realiza el mantenimiento de las cantidad de las bicicleas entrantes y salientes.
   *
   *
   * @param bookingDetails
   * @param status
   * @return Mono
   */
  public Mono<BookingDetailDto> updateInventory(BookingDetailDto bookingDetails,
                                                InventoryStatus status) {
    return Mono.defer(() -> {
      if (bookingDetails == null) {
        return Mono.error(new Exception("Update Inventory, BookingDetails are null."));
      }
      String bikeId = bikeRentalService.getBikeRentalByBikeRentId(bookingDetails.getBookingId()).block().getBikeId();
      String stationId = (InventoryStatus.DECREMENT.compareTo(status) == 0) ? bookingDetails.getOriginStationId()
          : bookingDetails.getDestinationStationId();
      return stationRepository.findByStationId(stationId)
          .map(det -> {
              det.setBikes(getBikeToModify(det, status, bikeId));
              return det;
          })
          .flatMap(stationRepository::save)
          .map(saved -> bookingDetails);
    });
  }

  // @SuppressWarnings("unchecked")
  private Collection<BikeVo> getBikeToModify(StationDocument stationDocument, InventoryStatus status, String bikeId) {
    return Stream.of(stationDocument.getBikes()).reduce(new LinkedList<>(), (result, element) -> {
      element.stream().filter((item) -> item.getBikeId().equals(bikeId))
          .forEach(item -> item.setQuantity(incrementOrDecrement().apply(item.getQuantity(), status)));
      return element;
    });
  }

  /**
   * método opera las cantidad de las bicicletas incrementando o decrementado su valor.

   * @return int
   */
  private BiFunction<Integer, InventoryStatus, Integer> incrementOrDecrement() {
    return (quantity, status) -> {
      if (quantity == 0 && InventoryStatus.DECREMENT.compareTo(status) == 0) {
        return quantity;
      }
      if (InventoryStatus.DECREMENT.compareTo(status) == 0) {
        log.info("[bike] decrement in one {} - 1", quantity);
        return --quantity;
      } else if (InventoryStatus.INCREMENT.compareTo(status) == 0) {
        log.info("[bike] increment in one {} + 1", quantity);
        return ++quantity;
      }
      return quantity;
    };

  }

}
