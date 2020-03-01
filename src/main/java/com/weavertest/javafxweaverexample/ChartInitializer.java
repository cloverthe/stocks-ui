package com.weavertest.javafxweaverexample;

import com.weavertest.javafxweaverexample.JavaFxApplication.StageReadyEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ChartInitializer implements ApplicationListener<StageReadyEvent> {
    private ApplicationContext applicationContext;
    private String applicationTitle;

    @Autowired
    public ChartInitializer(ApplicationContext applicationContext,
                            @Value("${spring.application.ui.title}") String applicationTitle) {
        this.applicationContext = applicationContext;
        this.applicationTitle = applicationTitle;
    }


    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ChartController.class);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle(applicationTitle);
        stage.show();
    }
}
