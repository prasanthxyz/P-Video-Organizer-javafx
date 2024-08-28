package com.wordpress.prasanthxyz.pvorg.models;

public class Combination {
    private String videoName;
    private String galleryName;

    public Combination(String videoName, String galleryName) {
        this.videoName = videoName;
        this.galleryName = galleryName;
    }

    public String getVideoName() {
        return videoName;
    }

    public String getGalleryName() {
        return galleryName;
    }
}
