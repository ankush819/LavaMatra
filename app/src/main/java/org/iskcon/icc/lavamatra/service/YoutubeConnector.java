package org.iskcon.icc.lavamatra.service;

import android.content.Context;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;

import org.iskcon.icc.lavamatra.Model.MovieItemModel;
import org.iskcon.icc.lavamatra.R;
import org.iskcon.icc.lavamatra.util.Config;
import org.iskcon.icc.lavamatra.util.LogHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ankush on 25-05-2017.
 */

/*

 */

//TODO : Have a builder template method for youtube query which all methods can use
public class YoutubeConnector {

    private static final String TAG = YoutubeConnector.class.getSimpleName();

    private YouTube youTube;
    private Context context;
    private static final String GOOGLE_API_KEY = Config.GOOGLE_API_KEY;
    private static final String YOUTUBE_USERNAME = Config.YOUTUBE_USERNAME;

    public YoutubeConnector(Context context) {
        this.context = context;
        this.youTube = getYoutubeService();
    }

    private YouTube getYoutubeService() {
        youTube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) throws IOException {

            }
        }).setApplicationName(context.getString(R.string.app_name)).build();
        return youTube;
    }

    //See if we can eliminate this method and directly hardCode our channelID in the Config
    //It will save up our Quota when the app scales
    private String getChannelIdForUsername(String youtubeUsername) {
        ChannelListResponse response = null;
        YouTube.Channels.List channel;
        String channelId;
        try {
            channel = youTube.channels().list("snippet");
            channel.setKey(GOOGLE_API_KEY);
            channel.setFields("items(id)");
            channel.setForUsername(YOUTUBE_USERNAME);
            channel.getForUsername();
            response = channel.execute();
        } catch (IOException e) {
            LogHelper.log(TAG, "debug", "IOException when getting channelID");
        }
        List<Channel> results = response.getItems();
        channelId = results.get(0).getId();
        LogHelper.log(TAG, "debug", "My channel ID is " + channelId);
        return channelId;
    }

    //ONE PLAYLIST IN YOUTUBE = ONE CATEGORY IN APP
    //Right now we will send for only one playlist i.e one category.
    //We need a different model to handle multiple playlists.
    //Can convert this method to getMovieItemsOfPlayList later on and have a public method call this.
    public List<MovieItemModel> getMovieItems() {
        PlaylistItemListResponse response = null;
        YouTube.PlaylistItems.List playlistItems;
        List<MovieItemModel> items = new ArrayList<>();
        List<PlaylistItem> results = null;
        try {
            playlistItems = youTube.playlistItems().list("snippet"); //TODO Place these strings as static above
            playlistItems.setKey(GOOGLE_API_KEY);
            playlistItems.setPlaylistId(getFirstPlaylistID(getChannelIdForUsername(YOUTUBE_USERNAME))); //TODO Handle null cases for channelId. Split it into 2 sentences.
            response = playlistItems.execute();
            results = response.getItems();
            for (PlaylistItem playlistItem : results) {
                MovieItemModel itemModel = new MovieItemModel();
                itemModel.setTitle(playlistItem.getSnippet().getTitle());
                itemModel.setMovieId(playlistItem.getId());
                itemModel.setThumbnailUrl(playlistItem.getSnippet().getThumbnails().getDefault().getUrl());
                items.add(itemModel);
            }
            return items;
        } catch (IOException e) {
            LogHelper.log(TAG, "debug", "IOException when getting playlist");
            return null;
        }
    }

    //Method used to test. Delete in production.
    private String getFirstPlaylistID(String channelId) {
        PlaylistListResponse response = null;
        YouTube.Playlists.List playlist;
        String firstPlaylistId;
        try {
            playlist = youTube.playlists().list("snippet");
            playlist.setKey(GOOGLE_API_KEY);
            playlist.setChannelId(channelId);
            playlist.getChannelId();
            response = playlist.execute();
            List<Playlist> results = response.getItems();
            firstPlaylistId = results.get(0).getId();
            LogHelper.log(TAG, "debug", "My firstPlayListId is " + firstPlaylistId);
            return firstPlaylistId;
        } catch (IOException e) {
            LogHelper.log(TAG, "debug", "IOException when getting playListId");
            return null;
        }
    }
}
