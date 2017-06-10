package org.iskcon.icc.lavamatra.categories.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.iskcon.icc.lavamatra.R;
import org.iskcon.icc.lavamatra.categories.CategoryFragment;
import org.iskcon.icc.lavamatra.categories.model.Category;
import org.iskcon.icc.lavamatra.util.LogHelper;

import java.util.ArrayList;

/**
 * Created by Ankush on 10-06-2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    //TODO : Implement all methods of category adapter
    private static final String TAG = CategoryAdapter.class.getSimpleName();

    private ArrayList<Category> categories;
    private Context context;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogHelper.log(TAG, "debug", "onCreateViewHolder() called");
        View imageView = LayoutInflater.from(context)
                .inflate(R.layout.category_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(imageView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LogHelper.log(TAG, "debug", "onBindViewHolder() called");
        Category category = categories.get(position);
        //TODO : Set the imageResource from path here
        holder.textView.setText(category.getDisplayName());

        int resId = context.getResources().getIdentifier(category.getThumbnailRawPath(), "drawable", context.getPackageName());
        holder.imageView.setImageResource(resId);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogHelper.log(TAG, "debug", "Category was clicked!");
                //TODO : Call Fragment Manager from here
                //FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                //fragmentManager.beginTransaction()
                //        .add(R.id.categoryLayout, CategoryFragment.newInstance())
                //        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        LogHelper.log(TAG, "debug", "getItemCount() called");
        return categories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.categoryImage);
            textView = (TextView) itemView.findViewById(R.id.categoryName);
        }
    }
}
