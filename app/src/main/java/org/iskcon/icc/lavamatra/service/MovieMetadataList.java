package org.iskcon.icc.lavamatra.service;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.services.youtube.YouTube;

import org.iskcon.icc.lavamatra.Model.MediaModel;
import org.iskcon.icc.lavamatra.Model.MovieItemModel;
import org.iskcon.icc.lavamatra.util.LogHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ankush on 25-05-2017.
 */

/*
    To fetch movieMetadata from Youtube.
    MovieMetadataList ---> YoutubeDataAPI (YoutubeConnector)
         |      |             |
         |      |             \------> Sets the list of movieItemModel for a Category/PlayList (FIX IT for all Categories)
         |      |
         |      \-------> Can fetch from other sources also, in the future.
         |
         v
     Sets MediaModel
*/

public class MovieMetadataList extends AsyncTask<Void, Void, List<MediaModel>> {
    //TODO 1 : Get the movieMetadata and return a list of mediaModels to be set in the CategoryFragment adapter
    //Should use the repository service to fetch the movie Metadata from Youtube
    //The images has to be fetched into a bitmap and set in the movieModel. To do this we will use another repository for images.
    //The caller will not pass any playListID, the movieMetadataList will give back everything that needs to go to the controller fragment.

    private static final String TAG = MovieMetadataList.class.getSimpleName();
    private Context context;
    public AsyncResponse delegate = null;
    public MovieMetadataList(Context context) {
        this.context = context;
    }


    @Override
    protected List<MediaModel> doInBackground(Void... params) {
        YoutubeConnector yc = new YoutubeConnector(context);
        List<MediaModel> mediaModels = new ArrayList<>();
        List<MovieItemModel> newMovieItemModels = yc.getMovieItems();
        for (MovieItemModel movieItemModel : newMovieItemModels) {
            MediaModel mediaModel = new MediaModel();
            mediaModel.setMovieItemModel(movieItemModel);
            mediaModels.add(mediaModel);
            LogHelper.log(TAG, "debug", "The mediaModel in AsyncTask is - " + mediaModel);
        }
        return mediaModels;
    }

    @Override
    protected void onPostExecute(List<MediaModel> mediaModelList) {
        LogHelper.log(TAG, "debug", "Calling processFinish()");
        delegate.processFinish(mediaModelList);
    }
}
