package com.weavertest.stockclient.clients;

import com.weavertest.stockclient.model.StockPrice;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class RSocketStockClient implements StockClient {
    private final RSocketRequester rSocketRequester;

    public RSocketStockClient(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @Override
    public Flux<StockPrice> pricesFor(String symbol) {
        return rSocketRequester.route("stockPrices")
                .data(symbol)
                .retrieveFlux(StockPrice.class)
                .log("rsocket");
    }
}
