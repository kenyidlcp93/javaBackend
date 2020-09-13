package pe.com.example.bikerental.expose.web;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pe.com.example.bikerental.business.fn03.CountBikeStationService;
import pe.com.example.bikerental.business.fn01.BookingService;
import pe.com.example.bikerental.business.fn02.CompletionOrCancellationService;
import pe.com.example.bikerental.models.api.fn02.request.CountBikeStation;
import pe.com.example.bikerental.models.api.fn03.request.RentalBikeRequest;
//import pe.com.example.bikerental.thirdparty.redis.HistoryStatus;
//import pe.com.example.bikerental.thirdparty.redis.InteractionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bike-rental/flux/v1")
public class CacheController {

  private static final Logger log = LoggerFactory.getLogger(CacheController.class);

  private final BookingService bookingService;
  private final CountBikeStationService countBikeByStationService;
  private final CompletionOrCancellationService completionOrCancellationService;

  public CacheController(BookingService bookingService,
                         CountBikeStationService countBikeByStationService,
                         CompletionOrCancellationService completionOrCancellationService) {
    this.bookingService = bookingService;
    this.countBikeByStationService = countBikeByStationService;
    this.completionOrCancellationService = completionOrCancellationService;
  }

  @GetMapping(value = "/report", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE})
  @ResponseStatus(HttpStatus.OK)
  public Flux<List<CountBikeStation>> getcountBikeByStation() {
    return countBikeByStationService.countBikeByStation();
  }

  @PostMapping(value = "/rents", consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<?> createBooking(@RequestBody RentalBikeRequest payload) throws Exception {
    return bookingService.createBikeRental().apply(payload);
  }

  @PatchMapping(value = "/rents/{bookingId}", consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> completingBikeBooking(@PathVariable("bookingId") Integer bookingId) throws Exception {
    log.info("[starting completing booking]");
    return completionOrCancellationService.completionOrCancellationRentalByRentId(Map.of("rentId", bookingId, "interaction", ""));
  }



  /*private final BookingService bookingService;
  private final CompletionOrCancellationService corcService;
  private final InteractionService interactionService;

  @PostMapping(value = "/rents", consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<?> createBooking(@RequestBody RentalBikeRequest payload) throws Exception {
    return bookingService.createBikeRental().apply(payload);
  }

  public CacheController(BookingService bookingService, InteractionService interactionService,
      CompletionOrCancellationService corcService) {
    this.bookingService = bookingService;
    this.interactionService = interactionService;
    this.corcService = corcService;
  }

  @PostMapping(value = "/rents", consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<?> createBooking(@RequestBody RentalBikeRequest payload) throws Exception {
    return bookingService.createBikeRental().apply(payload);
  }

  @PatchMapping(value = "/rents/{bookingId}", consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> completingBikeBooking(@PathVariable("bookingId") Integer bookingId) throws Exception {
    log.info("[starting completing booking]");
    return corcService.completionOrCancellationRentalByRentId(Map.of("rentId", bookingId, "interaction", HistoryStatus.COMPLETED));
  }

  @DeleteMapping(value = "/rents/{bookingId}", consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> cancellingBikeBooking(@PathVariable("bookingId") Integer bookingId) throws Exception {
    log.info("[starting cancelling booking]");
    return corcService
        .completionOrCancellationRentalByRentId(Map.of("rentId", bookingId, "interaction", HistoryStatus.CANCELLING));
  }

  @GetMapping(value = "/histories/{userId}", produces = {MediaType.APPLICATION_STREAM_JSON_VALUE})
  @ResponseStatus(HttpStatus.OK)
  public Flux<InteractionDto> getHistoryInteractionByUserId(@PathVariable("userId") String userId) {
    return interactionService.getHistoryByUserId(userId);
  }
*/
}
