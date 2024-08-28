package com.wordpress.prasanthxyz.pvorg.models;

import java.util.List;

public class VideoRelations {
    private List<String> galleries;
    private List<String> tags;

    public VideoRelations() {
    }

    public List<String> getGalleries() {
        return galleries;
    }

    public void setGalleries(List<String> galleries) {
        this.galleries = galleries;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
