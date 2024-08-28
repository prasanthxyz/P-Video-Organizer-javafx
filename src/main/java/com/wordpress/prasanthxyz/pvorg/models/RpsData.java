package com.wordpress.prasanthxyz.pvorg.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

public class RpsData {
    private final String RPS_CONFIG_FILENAME = "pvorg.json";

    private List<String> videoNames;
    private List<String> galleryNames;
    private List<String> tagNames;
    private Map<String, List<String>> videoGalleries;
    private Map<String, List<String>> videoTags;
    private Map<String, List<String>> galleryImages;
    private Set<String> selectedVideos;
    private Set<String> selectedGalleries;
    private Set<String> selectedTags;
    private List<Combination> combinations;
    private int combinationIndex;

    public RpsData() {
        String configFilePath = System.getProperty("user.home") + File.separator + RPS_CONFIG_FILENAME;
        File jsonFile = new File(configFilePath);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RpsConfig rpsConfig = objectMapper.readValue(jsonFile, RpsConfig.class);
            loadVideoNames(rpsConfig);
            loadGalleryNames(rpsConfig);
            loadTagNames(rpsConfig);
            loadVideoGalleries(rpsConfig);
            loadVideoTags(rpsConfig);
            loadGalleryImages(rpsConfig);
            selectedVideos = new HashSet<>(videoNames);
            selectedGalleries = new HashSet<>(galleryNames);
            selectedTags = new HashSet<>();
            loadCombinations();
            combinationIndex = 0;
        } catch (Exception e) {
            System.out.println("Error reading config file: " + configFilePath + e.toString());
            System.exit(1);
        }
    }

    public List<String> getVideoNames() {
        return videoNames;
    }

    public List<String> getGalleryNames() {
        return galleryNames;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public Map<String, List<String>> getVideoGalleries() {
        return videoGalleries;
    }

    public Map<String, List<String>> getVideoTags() {
        return videoTags;
    }

    public Map<String, List<String>> getGalleryImages() {
        return galleryImages;
    }

    public Set<String> getSelectedVideos() {
        return selectedVideos;
    }

    public void setSelectedVideos(Set<String> selectedVideos) {
        this.selectedVideos = selectedVideos;
    }

    public Set<String> getSelectedGalleries() {
        return selectedGalleries;
    }

    public void setSelectedGalleries(Set<String> selectedGalleries) {
        this.selectedGalleries = selectedGalleries;
    }

    public Set<String> getSelectedTags() {
        return selectedTags;
    }

    public void setSelectedTags(Set<String> selectedTags) {
        this.selectedTags = selectedTags;
    }

    public Combination getCurrentCombination() {
        if (combinations.isEmpty()) {
            System.out.println("No combinations found");
            System.exit(2);
        }
        return combinations.get(combinationIndex);
    }

    public void moveToNextCombination() {
        if (combinations.isEmpty()) {
            System.out.println("No combinations found");
            System.exit(2);
        }
        combinationIndex = (combinationIndex + 1) % combinations.size();
    }

    public void moveToPreviousCombination() {
        if (combinations.isEmpty()) {
            System.out.println("No combinations found");
            System.exit(2);
        }
        combinationIndex = (combinationIndex - 1 + combinations.size()) % combinations.size();
    }

    private void loadVideoNames(RpsConfig rpsConfig) {
        videoNames = new ArrayList<>();
        for (File videoPath : Objects.requireNonNull(new File(rpsConfig.getVidPath()).listFiles())) {
            if (videoPath.isFile() && videoPath.getName().charAt(0) != '.') {
                videoNames.add(videoPath.getName());
            }
        }
    }

    private void loadGalleryNames(RpsConfig rpsConfig) {
        galleryNames = new ArrayList<>();
        for (File galleryPath : Objects.requireNonNull(new File(rpsConfig.getNamPath()).listFiles())) {
            if (galleryPath.isDirectory() && galleryPath.getName().charAt(0) != '.') {
                galleryNames.add(galleryPath.getName());
            }
        }
    }

    private void loadTagNames(RpsConfig rpsConfig) {
        tagNames = rpsConfig.getTags();
    }

    private void loadVideoGalleries(RpsConfig rpsConfig) {
        videoGalleries = new HashMap<>();
        for (String videoName : videoNames) {
            if (!rpsConfig.getVideoRelations().containsKey(videoName)) {
                videoGalleries.put(videoName, galleryNames);
            } else {
                videoGalleries.put(videoName, new ArrayList<>());
                for (String galleryName : rpsConfig.getVideoRelations().get(videoName).getGalleries()) {
                    if (galleryNames.contains(galleryName)) {
                        videoGalleries.get(videoName).add(galleryName);
                    }
                }
            }
        }
    }

    private void loadVideoTags(RpsConfig rpsConfig) {
        videoTags = new HashMap<>();
        for (String videoName : videoNames) {
            videoTags.put(videoName, new ArrayList<>());
            if (rpsConfig.getVideoRelations().containsKey(videoName)) {
                for (String tagName : rpsConfig.getVideoRelations().get(videoName).getTags()) {
                    if (tagNames.contains(tagName)) {
                        videoTags.get(videoName).add(tagName);
                    }
                }
            }
        }
    }

    private void loadGalleryImages(RpsConfig rpsConfig) {
        Set<String> imageExts = new HashSet<>(Arrays.asList(".png", ".jpg", ".jpeg", ".bmp", ".gif"));
        galleryImages = new HashMap<>();
        for (String galleryName : galleryNames) {
            List<String> imgList = new ArrayList<>();
            File galleryPath = new File(rpsConfig.getNamPath() + File.separator + galleryName);
            for (File imgPath : Objects.requireNonNull(galleryPath.listFiles())) {
                if (imgPath.isFile() && imgPath.getName().charAt(0) != '.' && imageExts.contains(imgPath.getName().substring(imgPath.getName().lastIndexOf('.')))) {
                    imgList.add(imgPath.getName());
                }
            }
            if (!imgList.isEmpty())
                galleryImages.put(galleryName, imgList);
        }
    }

    private void loadCombinations() {
        combinations = new ArrayList<>();
        for (String video : selectedVideos) {
            List<String> videoGalleries = this.videoGalleries.get(video);
            List<String> videoTags = this.videoTags.get(video);
            if (!selectedTags.isEmpty() && !selectedTags.containsAll(videoTags)) {
                continue;
            }
            for (String gallery : videoGalleries) {
                if (selectedGalleries.contains(gallery)) {
                    combinations.add(new Combination(video, gallery));
                }
            }
        }
    }
}
