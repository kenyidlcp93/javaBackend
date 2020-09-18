package pe.com.example.bikerental.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MssqlProperties {

    @Value("${application.sqlserver.connectionstring}")
    private String connectionstring;

    public String getConnectionstring() {
        return connectionstring;
    }

    public void setConnectionstring(String connectionstring) {
        this.connectionstring = connectionstring;
    }


}
