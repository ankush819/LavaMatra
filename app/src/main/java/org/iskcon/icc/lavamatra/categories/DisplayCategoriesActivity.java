package org.iskcon.icc.lavamatra.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.iskcon.icc.lavamatra.R;
import org.iskcon.icc.lavamatra.categories.adapter.CategoryAdapter;
import org.iskcon.icc.lavamatra.categories.model.Category;
import org.iskcon.icc.lavamatra.util.LogHelper;

import java.util.ArrayList;

/**
 * Created by Ankush on 10-06-2017.
 */

public class DisplayCategoriesActivity extends AppCompatActivity {

    private static final String TAG = DisplayCategoriesActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.log(TAG, "debug", "onCreate() called");

        setContentView(R.layout.activity_display_categories);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        CategoryProvider categoryProvider = new CategoryProvider(getApplicationContext());
        categories = categoryProvider.getCategories(); //TODO : Fill up categories with helper method from category provider

        LogHelper.log(TAG, "debug", "The categories are " + categories);
        categoryAdapter = new CategoryAdapter(getApplicationContext(), categories);
        recyclerView.setAdapter(categoryAdapter);
    }
}
