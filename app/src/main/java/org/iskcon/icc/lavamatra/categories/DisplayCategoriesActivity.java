package org.iskcon.icc.lavamatra.categories;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import org.iskcon.icc.lavamatra.R;
import org.iskcon.icc.lavamatra.categories.adapter.CategoryAdapter;
import org.iskcon.icc.lavamatra.categories.adapter.CategoryListAdapter;
import org.iskcon.icc.lavamatra.categories.model.Category;
import org.iskcon.icc.lavamatra.categories.model.CategoryModel;
import org.iskcon.icc.lavamatra.navigation.HandleNavigationMenu;
import org.iskcon.icc.lavamatra.util.LogHelper;

import me.relex.circleindicator.CircleIndicator;

import java.util.Timer;
import java.util.TimerTask;

import java.util.ArrayList;

/**
 * Created by Ankush on 10-06-2017.
 */

public class DisplayCategoriesActivity extends AppCompatActivity {

    private static final String TAG = DisplayCategoriesActivity.class.getSimpleName();

    /* TODO : To be deleted after testing */
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.jps_sample_logo, R.drawable.lavamatra_logo};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    /**
     * Till here
     **/

    private RecyclerView recyclerView;
    private ListView listView;
    private GridLayoutManager gridLayoutManager;
    private CategoryAdapter categoryAdapter;
    private CategoryListAdapter categoryListAdapter;
    private ArrayList<Category> categories;
    private ListView navList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.log(TAG, "debug", "onCreate() called");

        setContentView(R.layout.activity_display_categories);

        imageSlideInit();

        /*
        navList = (ListView) findViewById(R.id.navList);
        HandleNavigationMenu handleNavigationMenu = new HandleNavigationMenu();
        handleNavigationMenu.populateNavBar(DisplayCategoriesActivity.this, navList); */

        /* TODO : replacing this with listview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        CategoryProvider categoryProvider = new CategoryProvider(getApplicationContext());
        categories = categoryProvider.getCategories(); //TODO : Fill up categories with helper method from category provider

        FragmentManager fragmentManager = getSupportFragmentManager();

        LogHelper.log(TAG, "debug", "The categories are " + categories);
        categoryAdapter = new CategoryAdapter(getApplicationContext(), fragmentManager, categories);
        recyclerView.setAdapter(categoryAdapter); */

        listView = (ListView) findViewById(R.id.categoryView);
        //TODO : Get the list of CategoryModel and send it to the adapter.. a.k.a Talk to the YT class from here
        ArrayList<CategoryModel> list = new ArrayList<>();
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setPlaylistName("PLAYLIST 1");
        list.add(categoryModel);

        CategoryModel categoryModel2 = new CategoryModel();
        categoryModel2.setPlaylistName("PLAYLIST 2");
        list.add(categoryModel2);

        CategoryModel categoryModel3 = new CategoryModel();
        categoryModel3.setPlaylistName("PLAYLIST 3");
        list.add(categoryModel3);

        categoryListAdapter = new CategoryListAdapter(this, list); //TODO : Replace this with what i have talked in above todo
        listView.setAdapter(categoryListAdapter);

    }

    private void imageSlideInit() {
        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SliderAdapter(DisplayCategoriesActivity.this, XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);
    }
}

