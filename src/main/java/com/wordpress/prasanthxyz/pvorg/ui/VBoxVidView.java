package com.wordpress.prasanthxyz.pvorg.ui;

import com.wordpress.prasanthxyz.pvorg.models.RpsData;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class VBoxVidView extends VBox {
    private RpsData rpsData;
    private MediaView mediaView;
    Slider progressBar;
    private boolean isVideoPlaying;
    public VBoxVidView(RpsData rpsData) {
        super();
        this.rpsData = rpsData;

        this.isVideoPlaying = false;
        this.mediaView = new MediaView();
        this.mediaView.setPreserveRatio(true);
        this.mediaView.setFitWidth(500);

        this.progressBar = new Slider();
        this.progressBar.setMin(0);
        this.progressBar.setMax(100);
        this.progressBar.setValue(0);
        HBox.setHgrow(progressBar, Priority.ALWAYS);

        progressBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (progressBar.isValueChanging()) {
                double percentage = progressBar.getValue();
                this.seekVideo(percentage);
            }
        });

        HBox hBoxControls = new HBox(progressBar);
        this.getChildren().addAll(mediaView, hBoxControls);

        this.setStyle("-fx-border-color: red");
        this.setupVideoPlayer();
    }

    public void setupVideoPlayer() {
        String videoPath = rpsData.getVidPath() + File.separator + rpsData.getCurrentCombination().getVideoName();
        Media media = new Media(new File(videoPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!progressBar.isValueChanging()) {
                double progress = (newTime.toMillis() / mediaPlayer.getTotalDuration().toMillis()) * 100;
                progressBar.setValue(progress);
            }
        });
        mediaView.setMediaPlayer(mediaPlayer);
    }

    public void togglePlayPause() {
        if (isVideoPlaying) {
            mediaView.getMediaPlayer().pause();
            isVideoPlaying = false;
        } else {
            mediaView.getMediaPlayer().play();
            isVideoPlaying = true;
        }
    }

    public boolean isVideoPlaying() {
        return isVideoPlaying;
    }

    public void seekVideo(double percentage) {
        mediaView.getMediaPlayer().seek(mediaView.getMediaPlayer().getTotalDuration().multiply(percentage));
    }

    public Slider getProgressBar() {
        return progressBar;
    }
}
