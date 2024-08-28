package com.wordpress.prasanthxyz.pvorg.ui;

import com.wordpress.prasanthxyz.pvorg.models.RpsData;
import javafx.scene.layout.StackPane;

public class VBoxVidTgp extends StackPane {
    private RpsData rpsData;
    private boolean isTgpShown;
    private VBoxVidView vBoxVidView;
    private VBoxTgpView vBoxTgpView;
    public VBoxVidTgp(RpsData rpsData) {
        super();

        this.rpsData = rpsData;
        this.isTgpShown = true;
        this.setStyle("-fx-border-color: black");
        vBoxVidView = new VBoxVidView(rpsData);
        vBoxTgpView = new VBoxTgpView(rpsData);
        vBoxVidView.setVisible(false);
        this.getChildren().addAll(vBoxVidView, vBoxTgpView);
    }

    public void toggleTgpVid() {
        if (this.isTgpShown) {
            this.isTgpShown = false;
            vBoxTgpView.setVisible(false);
            vBoxVidView.setVisible(true);
            if (!vBoxVidView.isVideoPlaying())
                vBoxVidView.togglePlayPause();
        } else {
            this.isTgpShown = true;
            vBoxTgpView.setVisible(true);
            vBoxVidView.setVisible(false);
            if (vBoxVidView.isVideoPlaying())
                vBoxVidView.togglePlayPause();
        }
    }

    public VBoxVidView getVBoxVidView() {
        return vBoxVidView;
    }

    public VBoxTgpView getVBoxTgpView() {
        return vBoxTgpView;
    }
}
