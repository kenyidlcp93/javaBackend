package pe.com.example.bikerental.repository.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.com.example.bikerental.thirdparty.mongodb.StationDocument;
import reactor.core.publisher.Mono;

@Repository
public interface StationRepository extends ReactiveMongoRepository<StationDocument, String> {

  Mono<StationDocument> findByStationId(String stationId);

}