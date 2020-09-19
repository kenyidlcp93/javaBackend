package pe.com.example.bikerental.business.fn03;

import org.springframework.stereotype.Service;
import pe.com.example.bikerental.models.api.fn02.request.CountBikeStation;
import pe.com.example.bikerental.thirdparty.mongodb.StationDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public final class CountBikeStationServiceImpl implements CountBikeStationService {

  private final CountBikeStationSender countBikeStationSender;

  public CountBikeStationServiceImpl(CountBikeStationSender countBikeStationSender) {
    this.countBikeStationSender = countBikeStationSender;
  }

  @Override
  public Mono<List<StationDocument>> countBikeByStation() {
    return countBikeStationSender.getBikeByStation();
  }
}