package com.wordpress.prasanthxyz.pvorg.models;

import java.util.List;
import java.util.Map;

public class RpsConfig {
    private String vidPath;
    private String namPath;
    private List<String> tags;

    private Map<String, VideoRelations> videoRelations;

    public RpsConfig() {
    }

    public String getVidPath() {
        return vidPath;
    }

    public void setVidPath(String vidPath) {
        this.vidPath = vidPath;
    }

    public String getNamPath() {
        return namPath;
    }

    public void setNamPath(String namPath) {
        this.namPath = namPath;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Map<String, VideoRelations> getVideoRelations() {
        return videoRelations;
    }

    public void setVideoRelations(Map<String, VideoRelations> videoRelations) {
        this.videoRelations = videoRelations;
    }
}
