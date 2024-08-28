package com.wordpress.prasanthxyz.pvorg;

import com.wordpress.prasanthxyz.pvorg.models.RpsData;
import com.wordpress.prasanthxyz.pvorg.ui.MainUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private RpsData rpsData;
    public MainApplication() {
        rpsData = new RpsData();
    }

    @Override
    public void start(Stage stage) throws IOException {
        MainUI mainUI = new MainUI(rpsData);
        Scene scene = new Scene(mainUI, 320, 240);
        stage.setTitle("PVORG");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}