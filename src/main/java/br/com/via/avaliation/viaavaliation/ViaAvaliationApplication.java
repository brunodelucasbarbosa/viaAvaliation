package br.com.via.avaliation.viaavaliation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.DatabaseStartupValidator;

import javax.sql.DataSource;

@SpringBootApplication
public class ViaAvaliationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViaAvaliationApplication.class, args);
    }
}
