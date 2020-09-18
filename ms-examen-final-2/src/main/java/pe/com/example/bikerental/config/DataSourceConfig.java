package pe.com.example.bikerental.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import pe.com.example.bikerental.business.fn01.BookingCreationSender;

import javax.sql.DataSource;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
@EnableJpaRepositories(basePackages = "pe.com.example.bikerental.repository.mssql")
@EnableReactiveMongoRepositories(basePackages = "pe.com.example.bikerental.repository.mongodb")
public class DataSourceConfig {

  private static final org.slf4j.Logger log = LoggerFactory.getLogger(BookingCreationSender.class);

  @Autowired
  private MssqlProperties mssqlProperties;

  @Autowired
  private MongoProperties mongoProperties;

  @Bean
  public DataSource getDataSource() {

    log.info("[Sql Connection String from KeyVault : {}]", mssqlProperties.getConnectionstring());
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url(mssqlProperties.getConnectionstring());
    return dataSourceBuilder.build();
  }

  @Bean
  public MongoClient getMongoClient() {
    return MongoClients.create(new ConnectionString(mongoProperties.getConnectionstring()));
  }

  /**
   * método para exponer ReactiveMongoTemplate para el uso de ReactiveMongoOperations, capa
   * personalizada para el aceceso de datos reactivos desde mongodb.
   *
   * @param properties lectura de datos desde el properties
   * @return MongoTemplate
   */
  @Bean
  public ReactiveMongoTemplate reactiveMongoTemplate(MongoProperties properties) {
    return new ReactiveMongoTemplate(getMongoClient(), "OXNERDB");
  }
}
