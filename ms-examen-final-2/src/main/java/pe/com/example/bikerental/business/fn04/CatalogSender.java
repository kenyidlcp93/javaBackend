package pe.com.example.bikerental.business.fn04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pe.com.example.bikerental.models.api.fn02.request.CountBikeStation;
import pe.com.example.bikerental.models.api.fn04.request.Bike;
import pe.com.example.bikerental.models.api.fn04.request.CatalogResponse;
import pe.com.example.bikerental.repository.mssql.BikeRepository;
import pe.com.example.bikerental.repository.mssql.DetailStationRepository;
import pe.com.example.bikerental.repository.mssql.StationDtoRepository;
import pe.com.example.bikerental.thirdparty.mssql.BikeDto;
import pe.com.example.bikerental.thirdparty.mssql.StationDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public final class CatalogSender {

  private static final Logger log = LoggerFactory.getLogger(CatalogSender.class);

  private final BikeRepository bikeRepository;

  private final DetailStationRepository detailStationRepository;

  private final StationDtoRepository stationDtoRepository;

  private final EntityManager entityManager;

  /**
   *
   * @param bikeRepository
   * @param entityManager
   * @param detailStationRepository
   */
  public CatalogSender(BikeRepository bikeRepository,
                       EntityManager entityManager,
                       DetailStationRepository detailStationRepository,
                       StationDtoRepository stationDtoRepository) {
    this.bikeRepository = bikeRepository;
    this.entityManager = entityManager;
    this.detailStationRepository = detailStationRepository;
    this.stationDtoRepository = stationDtoRepository;
  }

  public Mono<CatalogResponse> getCatalogByStationId(String stationId) {
    return Mono.defer(() -> {
      return Mono.fromCallable(() -> stationDtoRepository.findById(stationId).orElse(null));
    }).map(parseToResponse)
        .map(getListBikeByStation())
        .doOnError((err) -> log.error("[Consult Bike Available for Station] error {}", err.getMessage()));
  }

  private Function<StationDto, CatalogResponse> parseToResponse = (sationDto) -> {
    return new CatalogResponse(sationDto.getStationId(), sationDto.getName(), sationDto.getLocation(), sationDto.getIsActive(), null);
  };


  private Function<CatalogResponse,CatalogResponse> getListBikeByStation() {
    return (dto) -> {
      List<Bike> listt = new ArrayList<Bike>();
      listt.addAll((List<Bike>)entityManager
          .createQuery("select b.bikeId, b.type, b.brand, b.priceByMinute, b.isActive from DetailStationsDto d join BikeDto b on b.bikeId = d.bikeId where d.stationId = ?1")
          .setParameter(1, dto.getStationId())
          .getResultList());
      dto.setListBike(listt);
      return dto;
    };
  };

  private Function<BikeDto, Bike> parseBikeToResponse = (bikeDto) -> {
    return new Bike(bikeDto.getBikeId(), bikeDto.getType(), bikeDto.getBrand(), bikeDto.getPriceByMinute(), bikeDto.getIsActive());
  };


/*
  public Flux<List<CountBikeStation>> getBikeByStation() {
    return Flux.just((List<CountBikeStation>)entityManager
        .createQuery("select stationId, sum(quantity) as available from DetailStationsDto group by stationId")
        .setFirstResult(0).setMaxResults(5)
        .getResultList())
        .doOnComplete(() -> log.info("[Consult Bike Available for Station] successful!"))
        .doOnError((err) -> log.error("[Consult Bike Available for Station] error {}", err.getMessage()));
  }

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
