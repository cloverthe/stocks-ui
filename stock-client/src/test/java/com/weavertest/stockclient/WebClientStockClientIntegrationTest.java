package com.weavertest.stockclient;

import com.weavertest.stockclient.clients.StockClient;
import com.weavertest.stockclient.clients.WebClientStockClient;
import com.weavertest.stockclient.model.StockPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientStockClientIntegrationTest {
    private WebClient webClient = WebClient.builder().build();

    @Test
    void shouldWork(){
        StockClient stockClient = new WebClientStockClient(webClient);
        String symbol = "TEST";
        Flux<StockPrice> prices = stockClient.pricesFor(symbol);
        Assertions.assertEquals(5, prices.take(5).count().block());
    }

}