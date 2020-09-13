package pe.com.example.bikerental.business.fn02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pe.com.example.bikerental.repository.mssql.BikeRentalRepository;
import pe.com.example.bikerental.repository.mssql.BookingDetailRepository;
import pe.com.example.bikerental.thirdparty.mssql.BookingDetailDto;
import pe.com.example.bikerental.thirdparty.mssql.BookingDto;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static pe.com.example.utils.Utils.getDatetimeSystem;

@Component
public final class CompletionOrCancellationSender {

  private static final Logger log = LoggerFactory.getLogger(CompletionOrCancellationSender.class);

  private final BikeRentalRepository rentalRepository;
  private final BookingDetailRepository bookingDetailRepository;

  /**
   * @param rentalRepository
   */
  public CompletionOrCancellationSender(BikeRentalRepository rentalRepository,
                                        BookingDetailRepository bookingDetailRepository) {
    this.rentalRepository = rentalRepository;
    this.bookingDetailRepository = bookingDetailRepository;
  }

  /**
   * método que sirve de entrypoint para procesar el flujo de datos para dar por terminado el
   * servicio de alquiler de una bicicleta. Lo primero que se realizar la validación de Id de la
   * renta, luego se realiza la busqueda en la base de datos para traer el detalle de la renta, el
   * objecto encontradom se procede a establecer los datos para dar por finalizado la renta, y como
   * último paso se realizá el llamdo al servicio de InteractionService para persistir la interacción
   * que se esta teniendo con el objecto (finalizar la renta.)
   *
   * @param rental -> dentro del mapa se espera que llegen el identificador y estado de la interacción.
   * @return Mono
   */
  public Mono<Void> completionOrCancellationRentalByRentId(Map<String, Object> rental) {
    return Mono.defer(() -> {
      Integer rentId = Integer.valueOf(rental.get("rentId").toString());
      if (!checkBikeRentId.test(rentId)) {
        return Mono.error(new Exception("[Bicycle RentId] not valid " + rentId));
      }
      return Mono.fromCallable(() -> rentalRepository.findById(rentId).orElse(null));
    })
        .flatMap(doingEndingOrCancellingRental()).then()
        .doOnSuccess((success) -> log.info("[Ending Rental] :) successful!"))
        .doOnError((err) -> log.error("[Ending Rental] :( error -> {}", err));
  }

  /**
   * método que nos ayuda con la definicíon de la acción que se realizará, esta decición se toma en
   * báse al estado de interacción (finalización o cancelación).
   *
   * @return Mono
   */
  private Function<BookingDto,  Mono<BookingDto>> doingEndingOrCancellingRental() {
    return (dto) -> {
      return rentalCompletionProcess().apply(dto);
    };
  };

  /**
   * método que realiza el establece los datos que van a cambiar para dar por finzalizadola renta
   * de la bicicleta.
   */
  private <T extends BookingDto> Function<T, Mono<T>> rentalCompletionProcess() {
    return (dto) -> {
      if (!checkBikeRentId.test(dto.getBookingId())) {
        return Mono.error(new Exception("[Bicycle RentId] not valid "));
      }
      dto.setCompleted(true);
      dto.setDateCompleted(getDatetimeSystem.get());

      BookingDetailDto bd = bookingDetailRepository.findById(dto.getBookingId()).get();
      bd.setEndDate(getDatetimeSystem.get());
      bookingDetailRepository.save(bd);

      return Mono.fromCallable(() -> rentalRepository.save(dto));
    };
  }

  /**
   * comprobar que el identificador de la renta de bicicleta es valido para el flujo.
   */
  private Predicate<Integer> checkBikeRentId = (id) -> {
    return (!ObjectUtils.isEmpty(id) && (id.intValue() > 0)) ? true : false;
  };

}
