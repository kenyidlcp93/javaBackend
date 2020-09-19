package pe.com.example.bikerental.repository.redis;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import pe.com.example.bikerental.thirdparty.redis.BikeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class BikeRepository {

  private final ReactiveRedisOperations<String, Object> opsTemplate;

  /**
   * @param opsTemplate
   */
  public BikeRepository(ReactiveRedisOperations<String, Object> opsTemplate) {
    this.opsTemplate = opsTemplate;
  }

  public Mono<BikeDto> save(BikeDto bike) {
    bike.setId(UUID.randomUUID().toString());
       return opsTemplate.opsForHash()
        .put(String.format("history:%s", bike.getId()), bike.getId(), bike)
        .flatMap((data) -> opsTemplate.opsForSet().add(String.format("history:userId:%s:idx", bike.getUserId()), bike.getId()))
        .flatMap((test) -> Mono.just(bike));
  }

  public Flux<BikeDto> findByUserId(String userId) {
    return opsTemplate.opsForSet().members(String.format("history:userId:%s:idx", userId))
        .cast(String.class)
        .flatMap((data) -> opsTemplate.opsForHash().values(String.format("history:%s", data))).cast(BikeDto.class);

  }

}
