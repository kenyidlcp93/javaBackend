package pe.com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.com.example.bikerental.repository.mongodb.StationRepository;
import pe.com.example.bikerental.thirdparty.mongodb.StationDocument;

@SpringBootApplication
public class ExamenFinalApplication {
  private static final Logger log = LoggerFactory.getLogger(ExamenFinalApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ExamenFinalApplication.class, args);
  }

  @Bean
  CommandLineRunner preLoadMongo(StationRepository station) throws Exception {

    return args -> {

      List<StationDocument> documents2 = new ArrayList<>();
      station.findAll().map(a -> documents2.add(a));

      ObjectMapper oMapper = new ObjectMapper();
      InputStream content = this.getClass().getResourceAsStream("/scripts/stations-init.json");

      StationDocument stationDocument = new StationDocument();
      stationDocument.setAvaiable("1");
      stationDocument.setId("6");
      stationDocument.setStationId("S0006");

      station.save(stationDocument);

      List<StationDocument> documents =
          oMapper.readValue(content, new TypeReference<List<StationDocument>>() {
          });
      log.info("[content init] {}", documents);
      station.saveAll(documents);
    };
  }
}
