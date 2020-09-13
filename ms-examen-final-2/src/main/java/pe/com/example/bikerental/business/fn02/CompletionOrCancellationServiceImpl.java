package pe.com.example.bikerental.business.fn02;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public final class CompletionOrCancellationServiceImpl implements CompletionOrCancellationService {

  private final CompletionOrCancellationSender corcSender;

  /**
   * @param corcSender
   */
  public CompletionOrCancellationServiceImpl(CompletionOrCancellationSender corcSender) {
    this.corcSender = corcSender;
  }

  /**
   *
   */
  @Override
  public Mono<Void> completionOrCancellationRentalByRentId(Map<String, Object> rental) {
    return corcSender.completionOrCancellationRentalByRentId(rental);
  }

}