package pe.com.example.bikerental.business.fn02;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface CompletionOrCancellationService {

  Mono<Void> completionOrCancellationRentalByRentId(Map<String, Object> rental);

}