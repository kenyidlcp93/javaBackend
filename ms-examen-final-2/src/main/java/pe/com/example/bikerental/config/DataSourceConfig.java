package pe.com.example.bikerental.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

  @Bean
  public DataSource getDataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    dataSourceBuilder.url("jdbc:sqlserver://srvazsqlexamen.database.windows.net:1433;database=azsqlexamen;user=kenyidlcp@srvazsqlexamen;password=Javabackend2020;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
    dataSourceBuilder.username("kenyidlcp");
    dataSourceBuilder.password("Javabackend2020");
    return dataSourceBuilder.build();
  }

}
