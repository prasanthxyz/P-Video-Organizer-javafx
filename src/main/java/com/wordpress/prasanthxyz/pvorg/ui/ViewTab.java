package com.wordpress.prasanthxyz.pvorg.ui;

import com.wordpress.prasanthxyz.pvorg.models.Combination;
import com.wordpress.prasanthxyz.pvorg.models.RpsData;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ViewTab extends Tab {
    private RpsData rpsData;
    private Label labelVid;
    private Label labelNam;
    private VBoxVidTgp vBoxVidTgp;
    private VBoxGal vBoxGal;
    public ViewTab(String text, RpsData rpsData) {
        super(text);

        this.rpsData = rpsData;

        this.labelVid = new Label("VID");
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        Button buttonTgpVid = new Button("TGP/VID");
        buttonTgpVid.setOnAction(event -> {
            this.vBoxVidTgp.toggleTgpVid();
        });

        Button buttonPrev = new Button("Prev");
        buttonPrev.setOnAction(event -> {
            this.navigateCombination(false);
        });
        Button buttonNext = new Button("Next");
        buttonNext.setOnAction(event -> {
            this.navigateCombination(true);
        });
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        this.labelNam = new Label("NAM");

        vBoxVidTgp = new VBoxVidTgp(rpsData);
        vBoxGal = new VBoxGal(rpsData);

        HBox hBoxControlsLeft = new HBox();
        hBoxControlsLeft.setAlignment(Pos.CENTER);
        hBoxControlsLeft.getChildren().addAll(labelVid, spacer1, buttonTgpVid);
        HBox.setHgrow(hBoxControlsLeft, Priority.ALWAYS);
        HBox hBoxControlsRight = new HBox(4);
        hBoxControlsRight.setAlignment(Pos.CENTER);
        hBoxControlsRight.getChildren().addAll(buttonPrev, buttonNext, spacer2, labelNam);
        HBox.setHgrow(hBoxControlsRight, Priority.ALWAYS);

        HBox hBoxControls = new HBox(4);
        hBoxControls.getChildren().addAll(hBoxControlsLeft, hBoxControlsRight);
        hBoxControls.widthProperty().addListener((observable, oldValue, newValue) -> {
            hBoxControlsLeft.setPrefWidth(newValue.doubleValue() * 0.75);
            hBoxControlsRight.setPrefWidth(newValue.doubleValue() * 0.25);
        });

        HBox hBoxView = new HBox(4);
        VBox.setVgrow(hBoxView, Priority.ALWAYS);
        hBoxView.getChildren().addAll(vBoxVidTgp, vBoxGal);
        hBoxView.widthProperty().addListener((observable, oldValue, newValue) -> {
            vBoxVidTgp.setPrefWidth(newValue.doubleValue() * 0.75);
            vBoxGal.setPrefWidth(newValue.doubleValue() * 0.25);
        });

        VBox vBoxViewTab = new VBox();
        vBoxViewTab.getChildren().addAll(hBoxControls, hBoxView);
        this.setContent(vBoxViewTab);

        this.updateCombination();

        vBoxViewTab.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().isDigitKey()) {
                int digit = Integer.parseInt(event.getText());
                if (digit >= 1 && digit <= 9) {
                    double seekPercentage = digit * 10 / 100.0;
                    vBoxVidTgp.getVBoxVidView().seekVideo(seekPercentage);
                }
            } else if (event.getCode() == KeyCode.LEFT) {
                vBoxVidTgp.getVBoxVidView().seekVideo(vBoxVidTgp.getVBoxVidView().getProgressBar().getValue() - 5);
            } else if (event.getCode() == KeyCode.RIGHT) {
                vBoxVidTgp.getVBoxVidView().seekVideo(vBoxVidTgp.getVBoxVidView().getProgressBar().getValue() + 5);
            } else if (event.getCode() == KeyCode.SPACE) {
                vBoxVidTgp.getVBoxVidView().togglePlayPause();
            } else if (event.getCode() == KeyCode.P) {
                vBoxVidTgp.toggleTgpVid();
            } else if (event.getCode() == KeyCode.N) {
                this.navigateCombination(true);
            } else if (event.getCode() == KeyCode.B) {
                this.navigateCombination(false);
            }
        });
        vBoxViewTab.requestFocus();
    }

    public void navigateCombination(boolean isNext) {
        if (isNext)
            this.rpsData.moveToNextCombination();
        else
            this.rpsData.moveToPreviousCombination();
        this.updateCombination();
    }

    private void updateCombination() {
        Combination combination = this.rpsData.getCurrentCombination();
        this.labelVid.setText(combination.getVideoName());
        this.labelNam.setText(combination.getGalleryName());
        this.vBoxVidTgp.getVBoxVidView().setupVideoPlayer();
        this.vBoxVidTgp.getVBoxTgpView().showCurrentTgp();
        this.vBoxGal.showCurrentGallery();
    }
}
