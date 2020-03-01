package com.weavertest.javafxweaverexample;

import com.weavertest.stockclient.StockPrice;
import com.weavertest.stockclient.WebClientStockClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@FxmlView("chart.fxml")
public class ChartController implements Consumer<StockPrice> {
    @Override
    public void accept(StockPrice stockPrice) {
        Platform.runLater( () ->
        seriesData.add(new XYChart.Data<>(String.valueOf(stockPrice.getTime().getSecond()),
                stockPrice.getPrice()))
        );
    }

    @FXML
    public LineChart<String, Double> chart;
    private ObservableList<XYChart.Data<String, Double>> seriesData = FXCollections.observableArrayList();
    private WebClientStockClient stockClient;

    @Autowired
    public ChartController(WebClientStockClient stockClient) {
        this.stockClient = stockClient;
    }

    @FXML
    public void initialize() {
        String symbol = "TEST";
        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
        data.add(new XYChart.Series<>(symbol, seriesData));
        chart.setData(data);

        stockClient.pricesFor(symbol).subscribe(this);
    }
}
