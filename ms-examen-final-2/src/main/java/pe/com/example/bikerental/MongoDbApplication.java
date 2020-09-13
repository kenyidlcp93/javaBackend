package pe.com.example.bikerental;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.com.example.bikerental.repository.mongodb.StationRepository;
import pe.com.example.bikerental.thirdparty.mongodb.StationDocument;
import brave.sampler.Sampler;

@SpringBootApplication
public class MongoDbApplication {

  private static final Logger log = LoggerFactory.getLogger(MongoDbApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(MongoDbApplication.class, args);
  }

  @Bean
  public Sampler getDefaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }

  @Bean
  CommandLineRunner preLoadMongo(StationRepository station) throws Exception {
    return args -> {
      station.deleteAll();
      ObjectMapper oMapper = new ObjectMapper();
      InputStream content = this.getClass().getResourceAsStream("/scripts/stations-init.json");
      List<StationDocument> documents =
          oMapper.readValue(content, new TypeReference<List<StationDocument>>() {
          });
      log.info("[content init] {}", documents);
      station.saveAll(documents);
    };
  }


}
