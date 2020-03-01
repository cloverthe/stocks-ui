package com.weavertest.stockclient;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class WebClientStockClient {
    private WebClient webClient;

    public WebClientStockClient(WebClient webClient) {
        this.webClient = webClient;
    }


    public Flux<StockPrice> pricesFor(String symbol) {
        return webClient.get()
                .uri("localhost:8080/stocks/{symbol}", symbol)
                .retrieve()
                .bodyToFlux(StockPrice.class);

    }
}
