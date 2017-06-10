package org.iskcon.icc.lavamatra.categories;

import android.content.Context;

import org.iskcon.icc.lavamatra.R;
import org.iskcon.icc.lavamatra.categories.model.Category;
import org.iskcon.icc.lavamatra.util.LogHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ankush on 10-06-2017.
 */

public class CategoryProvider {

    private static final String TAG = CategoryProvider.class.getSimpleName();

    private Context context;

    private static final String JSON_PARENT = "categories";
    private static final String JSON_CATEGORY = "category";
    private static final String JSON_PLAYLIST_NAME = "playlistName";
    private static final String JSON_DISPLAY_NAME = "displayName";
    private static final String JSON_SHORT_DESCRIPTION = "shortDescription";
    private static final String JSON_LONG_DESCRIPTION = "longDescription";
    private static final String JSON_THUMBNAIL = "thumbnailRawPath";

    public CategoryProvider(Context context) {
        this.context = context;
    }

    public ArrayList<Category> getCategories() {
        //TODO : Parse the raw/res json file containing categories and return the same to be set in adapter
        LogHelper.log(TAG, "debug", "getCategories called");
        ArrayList<Category> categories = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(readJsonFile());
            JSONObject jsonObjectResult = jsonObject.getJSONObject(JSON_PARENT);
            JSONArray jsonArray = jsonObjectResult.getJSONArray(JSON_CATEGORY);

            for (int i=0; i<jsonArray.length(); i++) {
                Category category = new Category();
                category.setPlaylistName(jsonArray.getJSONObject(i).getString(JSON_PLAYLIST_NAME));
                category.setDisplayName(jsonArray.getJSONObject(i).getString(JSON_DISPLAY_NAME));
                category.setThumbnailRawPath(jsonArray.getJSONObject(i).getString(JSON_THUMBNAIL));
                categories.add(category);
            }
        } catch (Exception e) {

        }
        LogHelper.log(TAG, "debug", "Categories are " + categories);
        return categories;
    }

    private String readJsonFile() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.category_json);
        /*ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int counter;
        try {
            counter = inputStream.read();
            LogHelper.log(TAG, "debug", String.valueOf(counter));
            while (counter != 1) {
                byteArrayOutputStream.write(counter);
                counter = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            LogHelper.log(TAG, "error", "Something went wrong when reading the JSON raw file");
        }
        LogHelper.log(TAG, "debug", "read from JSON raw file complete");
        */
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            inputStream.close();
        } catch (IOException e) {

        }
        return writer.toString();
    }
}
