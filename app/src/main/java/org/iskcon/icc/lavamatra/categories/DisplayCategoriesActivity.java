package org.iskcon.icc.lavamatra.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import org.iskcon.icc.lavamatra.R;
import org.iskcon.icc.lavamatra.categories.adapter.CategoryAdapter;
import org.iskcon.icc.lavamatra.categories.model.Category;
import org.iskcon.icc.lavamatra.navigation.HandleNavigationMenu;
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
    private ListView navList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.log(TAG, "debug", "onCreate() called");

        setContentView(R.layout.activity_display_categories);

        navList = (ListView) findViewById(R.id.navList);
        HandleNavigationMenu handleNavigationMenu = new HandleNavigationMenu();
        handleNavigationMenu.populateNavBar(DisplayCategoriesActivity.this, navList);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        CategoryProvider categoryProvider = new CategoryProvider(getApplicationContext());
        categories = categoryProvider.getCategories(); //TODO : Fill up categories with helper method from category provider

        FragmentManager fragmentManager = getSupportFragmentManager();

        LogHelper.log(TAG, "debug", "The categories are " + categories);
        categoryAdapter = new CategoryAdapter(getApplicationContext(), fragmentManager, categories);
        recyclerView.setAdapter(categoryAdapter);
    }
}
