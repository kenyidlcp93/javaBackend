package pe.com.example.bikerental.business.fn12;

import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import pe.com.example.bikerental.thirdparty.mssql.BookingDto;
import pe.com.example.bikerental.thirdparty.redis.HistoryStatus;
import pe.com.example.bikerental.thirdparty.redis.InteractionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public final class InteractionServiceImpl implements InteractionService {

  private final InteractionSender sender;

  /**
   * @param sender
   */
  public InteractionServiceImpl(InteractionSender sender) {
    this.sender = sender;
  }

  /**
   *
   */
  @Override
  public Mono<InteractionDto> interactionRegisterOnCache(BookingDto dto) {
    return sender.resgisterInteractionOnCache(dto);
  }

  /**
   * método donde se asigna el flag de creación, realizamos este paso para que, en el siguiente flujo
   * de datos, podamos indicarle al método que realicé la persistencia de las interaciones en redis
   * que se trata de una interacción de tipo creación.
   *
   * @param <T>
   * @param HistoryStatus
   * @return BookingDto
   */
  public <T extends BookingDto> BiFunction<T, HistoryStatus, T> assignFlaghistory() {
    return (rent, history) -> {
      rent.setHistoryStatus(history);
      return rent;
    };
  };


  /**
   *
   */
  @Override
  public Flux<InteractionDto> getHistoryByUserId(String userId) {
    return sender.getHistoryByUserId(userId);
  }

}