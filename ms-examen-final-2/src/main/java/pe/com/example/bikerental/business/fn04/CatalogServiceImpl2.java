package pe.com.example.bikerental.business.fn04;

import org.springframework.stereotype.Service;
import pe.com.example.bikerental.models.api.fn02.request.CountBikeStation;
import pe.com.example.bikerental.models.api.fn04.request.CatalogResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public final class CatalogServiceImpl implements CatalogService {

  private final CatalogSender countBikeStationSender;

  /**
   * @param countBikeStationSender
   */
  public CatalogServiceImpl(CatalogSender countBikeStationSender) {
    this.countBikeStationSender = countBikeStationSender;
  }

  /**
   *
   */
  @Override
  public Mono<CatalogResponse> getCatalogByStationId(String stationId) {
    return countBikeStationSender.getCatalogByStationId(stationId);
  }
}