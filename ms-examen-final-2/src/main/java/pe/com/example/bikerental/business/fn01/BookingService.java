package pe.com.example.bikerental.business.fn01;

import pe.com.example.bikerental.models.api.fn03.request.BikeRentalResponse;
import pe.com.example.bikerental.models.api.fn03.request.RentalBikeRequest;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * method interface for create new Booking.
 */
public interface BookingService {

  Mono<BikeRentalResponse> createBikeRental(RentalBikeRequest payload);
}