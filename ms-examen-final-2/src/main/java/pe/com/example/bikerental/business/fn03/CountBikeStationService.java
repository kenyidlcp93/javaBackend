package pe.com.example.bikerental.business.fn03;

import pe.com.example.bikerental.models.api.fn02.request.CountBikeStation;
import pe.com.example.bikerental.thirdparty.mongodb.StationDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CountBikeStationService {

  Mono<List<StationDocument>> countBikeByStation();

}