package com.weavertest.javafxweaverexample;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StocksUIStarter {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class, args);
    }
}
