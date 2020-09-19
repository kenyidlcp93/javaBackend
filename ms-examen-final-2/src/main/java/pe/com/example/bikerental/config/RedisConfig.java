package pe.com.example.bikerental.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import pe.com.example.bikerental.repository.mssql.BikeRentalRepository;
import pe.com.example.bikerental.thirdparty.redis.InteractionDto;


/*
 * Clase que se encarga de realizar las configuraciones para exponer objectos necsarios para
 * conectarse a Redis Class: RedisConfig
 */
@Configuration
// @EnableRedisRepositories(basePackageClasses = {InteractionRepository.class})
@EnableJpaRepositories(basePackageClasses = {BikeRentalRepository.class})
public class RedisConfig {

  @Value("${application.redis.host:localhost}")
  private String host;

  @Value("${application.redis.password:qwerty}")
  private String password;

  @Value("${application.redis.port:6379}")
  private int port;

  public String getHost() {
    return this.host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getPort() {
    return this.port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  /**
   * método que crea un bean para la conexión a Redis. Este médoto implementa una instancia de
   * RedisStandaloneConfiguration ya que para el laboratorio no se cuenta con un cluster.
   *
   * Data Redis usará Lettuce como cliente reactive.
   * Lettuce.
   * https://docs.spring.io/spring-data/redis/docs/2.3.2.RELEASE/reference/html/#redis:reactive:connectors
   *
   * @return RedisConnectionFactory
   */
  @Bean
  public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
    RedisStandaloneConfiguration standaloneAzure = new RedisStandaloneConfiguration();
    standaloneAzure.setHostName(getHost());
    standaloneAzure.setPassword(getPassword());
    standaloneAzure.setPort(getPort());
    return new LettuceConnectionFactory(standaloneAzure); // Lettuce sopports Non-Blocking requests
  }

  @Primary
  @Bean
  public <T> ReactiveRedisTemplate<String, T> reactiveRedisTemplate() {
    RedisSerializationContext<String, T> serializationContext =
        RedisSerializationContext
        .<String, T>newSerializationContext(new StringRedisSerializer())
      .hashKey(new StringRedisSerializer())
      .hashValue(new Jackson2JsonRedisSerializer<>(InteractionDto.class))
      .build();
    return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory(), serializationContext);
  }

}
