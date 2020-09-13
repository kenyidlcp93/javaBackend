package pe.com.example.bikerental.models.api.fn03.request;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Station implements Serializable {

  private static final long serialVersionUID = 1143702868657282272L;

  @NotNull
  @NotEmpty
  @Size(min = 5, max = 5)
  private String code;

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
