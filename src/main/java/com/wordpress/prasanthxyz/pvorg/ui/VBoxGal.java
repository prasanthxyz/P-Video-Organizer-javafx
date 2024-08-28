package com.wordpress.prasanthxyz.pvorg.ui;

import com.wordpress.prasanthxyz.pvorg.models.RpsData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VBoxGal extends VBox {
    private RpsData rpsData;
    private List<Image> images;
    private int currentImageIndex = 0;
    private ImageView imageView;
    private Timeline timeline;
    public VBoxGal(RpsData rpsData) {
        super();
        this.rpsData = rpsData;

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(100);

        this.getChildren().add(imageView);
        this.setStyle("-fx-border-color: black");
        this.showCurrentGallery();
    }

    public void showCurrentGallery() {
        this.stopSlideShow();
        String galleryName = rpsData.getCurrentCombination().getGalleryName();
        String galleryPath = rpsData.getNamPath() + File.separator + galleryName;
        List<String> galleryImages = rpsData.getGalleryImages().get(galleryName);
        images = new ArrayList<>();
        for (String galleryImage : galleryImages) {
            String imagePath = galleryPath + File.separator + galleryImage;
            try {
                InputStream stream = new FileInputStream(imagePath);
                Image image = new Image(stream);
                images.add(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        imageView.setImage(images.get(currentImageIndex));
        this.startSlideShow();
    }

    private void startSlideShow() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            currentImageIndex = (currentImageIndex + 1) % images.size();
            imageView.setImage(images.get(currentImageIndex));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void stopSlideShow() {
        if (timeline != null)
            timeline.stop();
    }
}
