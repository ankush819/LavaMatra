package org.iskcon.icc.lavamatra.categories.model;

import org.iskcon.icc.lavamatra.Model.MediaModel;

/**
 * Created by Ankush on 15-09-2017.
 */

public class CategoryModel {

    private String playlistName;
    private MediaModel mediaModel;
    private String playlistId;

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public MediaModel getMediaModel() {
        return mediaModel;
    }

    public void setMediaModel(MediaModel mediaModel) {
        this.mediaModel = mediaModel;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}
