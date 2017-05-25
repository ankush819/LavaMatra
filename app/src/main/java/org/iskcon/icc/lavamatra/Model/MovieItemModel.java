package org.iskcon.icc.lavamatra.Model;

import java.io.Serializable;

/**
 * Created by Ankush on 25-05-2017.
 */

public class MovieItemModel implements Serializable {
    private String movieId;
    private String title;
    private String thumbnailUrl;

    public MovieItemModel(String thumbnailUrl, String title, String movieId) {
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.movieId = movieId;
    }

    public MovieItemModel() {

    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getMovieId() {
        return movieId;
    }
}
