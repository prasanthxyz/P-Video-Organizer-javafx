package com.wordpress.prasanthxyz.pvorg.ui;

import com.wordpress.prasanthxyz.pvorg.models.RpsData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class VBoxTgpView extends VBox {
    private RpsData rpsData;
    private ImageView imageView;
    public VBoxTgpView(RpsData rpsData) {
        super();

        this.rpsData = rpsData;
        this.imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(this.prefWidthProperty());

        this.getChildren().add(imageView);
        this.showCurrentTgp();
    }

    public void showCurrentTgp() {
        String videoName = rpsData.getCurrentCombination().getVideoName();
        String tgpPath = rpsData.getVidPath() + File.separator + "img" + File.separator + videoName + ".jpg";
        try {
            InputStream stream = new FileInputStream(tgpPath);
            Image image = new Image(stream);
            imageView.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
            imageView.setImage(null);
        }
    }
}
