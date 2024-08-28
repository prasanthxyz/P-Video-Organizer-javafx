package com.wordpress.prasanthxyz.pvorg.ui;

import com.wordpress.prasanthxyz.pvorg.models.RpsData;
import javafx.scene.control.Tab;

public class ConfigTab extends Tab {
    private RpsData rpsData;
    public ConfigTab(String text, RpsData rpsData) {
        super(text);

        this.rpsData = rpsData;
    }
}
