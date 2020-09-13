package pe.com.example.bikerental.business.fn03;

import pe.com.example.bikerental.models.api.fn02.request.CountBikeStation;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CountBikeStationService {

  Flux<List<CountBikeStation>> countBikeByStation();

}