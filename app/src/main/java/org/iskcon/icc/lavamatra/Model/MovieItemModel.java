package org.iskcon.icc.lavamatra.Model;

import java.io.Serializable;

/**
 * Created by Ankush on 25-05-2017.
 */

public class MovieItemModel implements Serializable {
    String movieId;
    String title;
    String imageUri;

    public MovieItemModel(String imageUri, String title, String movieId) {
        this.imageUri = imageUri;
        this.title = title;
        this.movieId = movieId;
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

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getMovieId() {
        return movieId;
    }
}
