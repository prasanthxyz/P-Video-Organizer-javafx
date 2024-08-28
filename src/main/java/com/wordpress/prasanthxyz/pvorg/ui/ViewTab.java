package com.wordpress.prasanthxyz.pvorg.ui;

import com.wordpress.prasanthxyz.pvorg.models.Combination;
import com.wordpress.prasanthxyz.pvorg.models.RpsData;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ViewTab extends Tab {
    private RpsData rpsData;
    private Label labelVid;
    private Label labelNam;
    public ViewTab(String text, RpsData rpsData) {
        super(text);

        this.rpsData = rpsData;

        this.labelVid = new Label("VID");
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        Button buttonTgpVid = new Button("TGP/VID");

        Button buttonPrev = new Button("Prev");
        Button buttonNext = new Button("Next");
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        this.labelNam = new Label("NAM");

        VBoxVidTgp vBoxVidTgp = new VBoxVidTgp();
        VBoxGal vBoxGal = new VBoxGal();

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
    }

    private void updateCombination() {
        Combination combination = this.rpsData.getCurrentCombination();
        this.labelVid.setText(combination.getVideoName());
        this.labelNam.setText(combination.getGalleryName());
    }
}
