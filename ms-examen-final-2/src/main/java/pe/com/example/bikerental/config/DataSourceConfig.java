package pe.com.example.bikerental.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.com.example.bikerental.business.fn01.BookingCreationSender;

import javax.sql.DataSource;

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
    //log.info("[Mongo Connection String from KeyVault : {}]", mongoProperties.getConnectionstring());
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url(mssqlProperties.getConnectionstring());
    //dataSourceBuilder.url("jdbc:sqlserver://srvazsqlexamen.database.windows.net:1433;database=azsqlexamen;user=kenyidlcp@srvazsqlexamen;password=Javabackend2020;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
    return dataSourceBuilder.build();
  }

}
