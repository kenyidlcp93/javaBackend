package pe.com.example.bikerental.business.fn12;

import java.time.Duration;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pe.com.example.bikerental.repository.redis.InteractionRepository;
import pe.com.example.bikerental.thirdparty.mssql.BookingDto;
import pe.com.example.bikerental.thirdparty.redis.InteractionDto;
import pe.com.example.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public final class InteractionSender {

  private static final Logger log = LoggerFactory.getLogger(InteractionSender.class);
  private final InteractionRepository interactionRepository;

  /**
   * @param interactionRepository
   */
  public InteractionSender(InteractionRepository interactionRepository) {
    this.interactionRepository = interactionRepository;
  }

  /**
   * método que registra las interaciones (crear, completar o cancelar) la renta de una bicicleta.
   * El registro lo realiza en Cache (Redis), haciendo uso de las interfaces Repository que provee spring data.
   *
   * @return Mono
   */
  public  Mono<InteractionDto> resgisterInteractionOnCache(BookingDto dto) {
      InteractionDto history = new InteractionDto();
      history.setBookingId(dto.getBookingId());
      history.setUserId(dto.getUserId());
      history.setStatus(dto.getHistoryStatus());
      history.setRegisterDate(Utils.getDatetimeSystem.get().toString());
      return interactionRepository.save(history);
  }

  /**
   * método para consultar las interaciones que tiene el usuario por haber creado, cancelado o
   * terminado un alquier de bicicletas.
   *
   * @param userId String
   * @return Flux
   */
  public Flux<InteractionDto> getHistoryByUserId(String userId) {
    return interactionRepository.findByUserId(userId)
        .delayElements(Duration.ofSeconds(2L))
        .doOnComplete(() -> log.info("[Consult Interactions] successful!"))
        .doOnError((err) -> log.error("[Consult Interactions] error {}", err.getMessage()));
  }


}
