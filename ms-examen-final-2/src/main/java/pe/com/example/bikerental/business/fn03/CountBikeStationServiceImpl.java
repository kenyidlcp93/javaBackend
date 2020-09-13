package pe.com.example.bikerental.business.fn03;

import org.springframework.stereotype.Service;
import pe.com.example.bikerental.models.api.fn02.request.CountBikeStation;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public final class CountBikeStationServiceImpl implements CountBikeStationService {

  private final CountBikeStationSender countBikeStationSender;

  /**
   * @param countBikeStationSender
   */
  public CountBikeStationServiceImpl(CountBikeStationSender countBikeStationSender) {
    this.countBikeStationSender = countBikeStationSender;
  }

  /**
   *
   */
  @Override
  public Flux<List<CountBikeStation>> countBikeByStation() {
    return countBikeStationSender.getBikeByStation();
  }
}