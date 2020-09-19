package pe.com.example.bikerental.business.fn12;

import java.util.function.BiFunction;
import pe.com.example.bikerental.thirdparty.mssql.BookingDto;
import pe.com.example.bikerental.thirdparty.redis.HistoryStatus;
import pe.com.example.bikerental.thirdparty.redis.InteractionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InteractionService {

  Mono<InteractionDto> interactionRegisterOnCache(BookingDto dto);

  <T extends BookingDto> BiFunction<T, HistoryStatus, T> assignFlaghistory();

  Flux<InteractionDto> getHistoryByUserId(String userId);

}