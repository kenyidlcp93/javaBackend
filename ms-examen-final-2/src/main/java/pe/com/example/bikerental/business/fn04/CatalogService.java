package pe.com.example.bikerental.business.fn04;

import pe.com.example.bikerental.models.api.fn04.request.CatalogResponse;
import reactor.core.publisher.Mono;

public interface CatalogService {

  Mono<CatalogResponse> getCatalogByStationId(String stationId);

}