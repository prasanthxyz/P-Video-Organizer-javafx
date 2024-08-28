package com.wordpress.prasanthxyz.pvorg.ui;

import com.wordpress.prasanthxyz.pvorg.models.RpsData;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainUI extends VBox {
    private RpsData rpsData;
    public MainUI(RpsData rpsData) {
        super();
        this.rpsData = rpsData;

        ViewTab viewTab = new ViewTab("View", rpsData);

        ConfigTab configTab = new ConfigTab("Config", rpsData);

        TabPane homeTabPane = new TabPane();
        VBox.setVgrow(homeTabPane, Priority.ALWAYS);
        homeTabPane.getTabs().addAll(viewTab, configTab);
        homeTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        this.getChildren().add(homeTabPane);
    }
}
