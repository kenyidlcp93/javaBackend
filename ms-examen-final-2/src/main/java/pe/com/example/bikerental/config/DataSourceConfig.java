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
import org.springframework.context.annotation.Lazy;
import pe.com.example.bikerental.business.fn01.BookingCreationSender;

import javax.sql.DataSource;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Lazy
@Configuration
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
  public MongoClient mongoClient() {
    log.info("[Mongo Connection String from KeyVault : {}]", mongoProperties.getConnectionstring());
    CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
    CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
    return MongoClients.create(MongoClientSettings.builder()
        .applyConnectionString(new ConnectionString(mongoProperties.getConnectionstring()))
        .codecRegistry(codecRegistry)
        .build());
  }

}
