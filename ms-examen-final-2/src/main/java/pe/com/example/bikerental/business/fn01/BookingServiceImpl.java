package pe.com.example.bikerental.business.fn01;

import org.springframework.stereotype.Service;
import pe.com.example.bikerental.models.api.fn03.request.BikeRentalResponse;
import pe.com.example.bikerental.models.api.fn03.request.RentalBikeRequest;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class BookingServiceImpl implements BookingService {

  private final BookingCreationSender sender;

  public BookingServiceImpl(final BookingCreationSender sender) {
    this.sender = sender;
  }

  @Override
  public Function<RentalBikeRequest, Mono<BikeRentalResponse>> createBikeRental() {
    return (payload) -> sender.createBookingAndDetails().apply(payload);
  }

}
