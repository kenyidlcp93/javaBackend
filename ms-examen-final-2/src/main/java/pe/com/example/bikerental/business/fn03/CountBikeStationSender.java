package pe.com.example.bikerental.business.fn03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pe.com.example.bikerental.business.fn13.RentalService;
import pe.com.example.bikerental.models.api.fn02.request.CountBikeStation;
import pe.com.example.bikerental.repository.mongodb.StationRepository;
import pe.com.example.bikerental.repository.mssql.BikeRentalRepository;
import pe.com.example.bikerental.repository.mssql.DetailStationRepository;
import pe.com.example.bikerental.thirdparty.mongodb.StationDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;

@Component
public final class CountBikeStationSender {

  private static final Logger log = LoggerFactory.getLogger(CountBikeStationSender.class);

  private final BikeRentalRepository rentalRepository;

  private final StationRepository stationRepository;

  private final EntityManager entityManager;

  /**
   * @param rentalRepository
   */
  public CountBikeStationSender(BikeRentalRepository rentalRepository,
                                EntityManager entityManager,
                                StationRepository stationRepository) {
    this.rentalRepository = rentalRepository;
    this.entityManager = entityManager;
    this.stationRepository = stationRepository;
  }

  public Mono<List<StationDocument>> getBikeByStation() {
    return stationRepository.findAll().collectList();
  }

/*
public Flux<List<CountBikeStation>> getBikeByStation() {
    return Flux.just((List<CountBikeStation>)entityManager
        .createQuery("select stationId, sum(quantity) as available from DetailStationsDto group by stationId")
        .getResultList())
        .doOnComplete(() -> log.info("[Consult Bike Available for Station] successful!"))
        .doOnError((err) -> log.error("[Consult Bike Available for Station] error {}", err.getMessage()));
  }

  public Flux<List<CountBikeStation>> getBikeByStation() {
    return Flux.just(detailStationRepository.findAll())
        .flatMap(parseToResponse)
        .doOnComplete(() -> log.info("[Consult Bike Available for Station] successful!"))
        .doOnError((err) -> log.error("[Consult Bike Available for Station] error {}", err.getMessage()));
  }

  private Function<List<DetailStationsDto>, Flux<List<CountBikeStation>>> parseToResponse = (detailStationsDtoList) -> {
    return Flux.fromIterable(detailStationsDtoList)
        .map(dto -> new CountBikeStation(dto.getStationId(), dto.getQuantity()))
        .collectList().flux();
  };

  private Function<List<StationDocument>, Flux<List<CountBikeStation>>> parseToResponse = (detailStationsDtoList) -> {
    return Flux.fromIterable(detailStationsDtoList)
        .map(dto -> new CountBikeStation(dto.getStationId(), Integer.parseInt(dto.getAvaiable())))
        .collectList().flux();
  };

  */

}
