package org.iskcon.icc.lavamatra.categories.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import org.iskcon.icc.lavamatra.Model.MediaModel;
import org.iskcon.icc.lavamatra.R;
import org.iskcon.icc.lavamatra.util.LogHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ankush on 25-05-2017.
 */

public class CategoryListRecyclerAdapter extends RecyclerView.Adapter<CategoryListRecyclerAdapter.ViewHolder> {

    private static final String TAG = CategoryListRecyclerAdapter.class.getSimpleName();
    ArrayList<MediaModel> mediaModelList;
    Context context;

    public CategoryListRecyclerAdapter(Context context, ArrayList<MediaModel> mediaModelList) {
        this.mediaModelList = mediaModelList;
        this.context = context;
        LogHelper.log(TAG, "debug", "CategoryListRecyclerAdapter newInstance");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogHelper.log(TAG, "debug", "onCreateViewHolder() called");
        View imageView = LayoutInflater.from(context)
                .inflate(R.layout.adapter_image_item_test, parent, false);
        ViewHolder viewHolder = new ViewHolder(imageView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LogHelper.log(TAG, "debug", "onBindViewHolder() called");
        holder.imageView.setImageResource(R.drawable.jps_test);
    }

    @Override
    public int getItemCount() {
        LogHelper.log(TAG, "debug", "getItemCount() called");
        return mediaModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.movieImage);
        }
    }

}
