package br.com.via.avaliation.viaavaliation.observability;

import br.com.via.avaliation.viaavaliation.service.SellerService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class SellersMetrics implements MeterBinder {

    @Autowired
    private SellerService sellerService;

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        Gauge.builder("sellers_count", this, value -> sellerService.getCountSellers())
                .description("Number of sellers")
                .tags(Tags.of("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))))
                .baseUnit("count_sellers")
                .register(meterRegistry);
    }

    //TODO : Adicionar métricas para o número de vendedores de cada tipo de contrato
}
