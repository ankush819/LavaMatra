package org.iskcon.icc.lavamatra.categories.model;

/**
 * Created by Ankush on 10-06-2017.
 */

// Utility class to provide the categories metadata information from a JSON raw file

public class Category {

    private String playlistName;
    private String displayName;
    private String thumbnailRawPath;
    private String longDescription;

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getThumbnailRawPath() {
        return thumbnailRawPath;
    }

    public void setThumbnailRawPath(String thumbnailRawPath) {
        this.thumbnailRawPath = thumbnailRawPath;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

}
