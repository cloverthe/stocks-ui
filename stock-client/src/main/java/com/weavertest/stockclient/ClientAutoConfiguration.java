package com.weavertest.stockclient;

import com.weavertest.stockclient.clients.RSocketStockClient;
import com.weavertest.stockclient.clients.StockClient;
import com.weavertest.stockclient.clients.WebClientStockClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientAutoConfiguration {
    @Bean
    @Profile(ClientProfiles.SSE)
    public StockClient webClientStockClient(WebClient webClient) {
        return new WebClientStockClient(webClient);
    }

    @Bean
    @Profile(ClientProfiles.RSOCKET)
    public StockClient rSocketStockClient(RSocketRequester rSocketRequester) {
        return new RSocketStockClient(rSocketRequester);
    }


    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        return builder.connectTcp("localhost", 7000)
                .block();
    }
}
