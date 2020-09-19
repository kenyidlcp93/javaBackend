package pe.com.example.bikerental.business.fn01;

import org.springframework.stereotype.Service;
import pe.com.example.bikerental.models.api.fn03.request.BikeRentalResponse;
import pe.com.example.bikerental.models.api.fn03.request.RentalBikeRequest;
import reactor.core.publisher.Mono;

@Service
public class BookingServiceImpl implements BookingService {

  private final BookingCreationSender sender;

  public BookingServiceImpl(final BookingCreationSender sender) {
    this.sender = sender;
  }

  @Override
  public Mono<BikeRentalResponse> createBikeRental(RentalBikeRequest payload) {
    return sender.createBookingAndDetails(payload);
  }
}
