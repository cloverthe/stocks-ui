package com.weavertest.stockclient.clients;

import com.weavertest.stockclient.model.StockPrice;
import reactor.core.publisher.Flux;

public interface StockClient {
    Flux<StockPrice> pricesFor(String symbol);
}
