package pe.com.example.bikerental.business.fn03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pe.com.example.bikerental.models.api.fn02.request.CountBikeStation;
import pe.com.example.bikerental.repository.mssql.BikeRentalRepository;
import pe.com.example.bikerental.repository.mssql.DetailStationRepository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public final class CountBikeStationSender {

  private static final Logger log = LoggerFactory.getLogger(CountBikeStationSender.class);

  private final BikeRentalRepository rentalRepository;

  private final DetailStationRepository detailStationRepository;

  private final EntityManager entityManager;

  /**
   * @param rentalRepository
   */
  public CountBikeStationSender(BikeRentalRepository rentalRepository,
                                EntityManager entityManager,
                                DetailStationRepository detailStationRepository) {
    this.rentalRepository = rentalRepository;
    this.entityManager = entityManager;
    this.detailStationRepository = detailStationRepository;
  }

  public Flux<List<CountBikeStation>> getBikeByStation() {
    return Flux.just((List<CountBikeStation>)entityManager
        .createQuery("select stationId, sum(quantity) as available from DetailStationsDto group by stationId")
        .setFirstResult(0).setMaxResults(5)
        .getResultList())
        .doOnComplete(() -> log.info("[Consult Bike Available for Station] successful!"))
        .doOnError((err) -> log.error("[Consult Bike Available for Station] error {}", err.getMessage()));
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
  };*/

}
