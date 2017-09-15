package org.iskcon.icc.lavamatra.categories.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.iskcon.icc.lavamatra.R;
import org.iskcon.icc.lavamatra.categories.model.Category;
import org.iskcon.icc.lavamatra.categories.model.CategoryModel;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Ankush on 15-09-2017.
 */

public class CategoryListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CategoryModel> categories;


    public CategoryListAdapter(Context context, ArrayList<CategoryModel> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.category_list_layout, null);
            viewHolder.categoryTitle = (TextView) convertView.findViewById(R.id.categoryHeading);
            viewHolder.moreButton = (Button) convertView.findViewById(R.id.clickForMore);
            viewHolder.categoryVideo1 = (ImageView) convertView.findViewById(R.id.categoryImage1);
            viewHolder.categoryVideo2 = (ImageView) convertView.findViewById(R.id.categoryImage2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.categoryTitle.setText(categories.get(position).getPlaylistName());
        return convertView;
    }


    public static class ViewHolder {
        TextView categoryTitle;
        Button moreButton;
        ImageView categoryVideo1;
        ImageView categoryVideo2;
    }
}
