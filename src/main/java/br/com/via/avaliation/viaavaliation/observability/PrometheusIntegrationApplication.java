package br.com.via.avaliation.viaavaliation.observability;

import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class PrometheusIntegrationApplication {

    final static Logger logger = Logger.getLogger(PrometheusIntegrationApplication.class.getName());

    public static void main(String[] args) {
        logger.info("Starting Prometheus Integration Application");
        SpringApplication.run(PrometheusIntegrationApplication.class, args);
    }

    @GetMapping("/")
    public ResponseEntity<String> createLogs() {
        logger.warning("This is a warning message");
        return ResponseEntity.ok().body("I'm fine.");
    }
}
