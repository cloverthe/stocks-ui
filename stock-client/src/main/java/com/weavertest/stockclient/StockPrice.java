package com.weavertest.stockclient;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockPrice {
    private String symbol;

    private Double price;
    private LocalDateTime time;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
