package pe.com.example.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Supplier;

public final class Utils {

  public static Supplier<LocalDateTime> getDatetimeSystem = () -> {
    return LocalDateTime.now(ZoneId.of("America/Lima"));
  };

}