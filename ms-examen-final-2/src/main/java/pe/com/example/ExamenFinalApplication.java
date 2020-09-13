package pe.com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import brave.sampler.Sampler;

@SpringBootApplication
@EnableAutoConfiguration
public class ExamenFinalApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExamenFinalApplication.class, args);
  }

  @Bean
  public Sampler getDefaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }

}
