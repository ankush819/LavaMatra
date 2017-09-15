package org.iskcon.icc.lavamatra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.iskcon.icc.lavamatra.categories.CategoryFragment;
import org.iskcon.icc.lavamatra.util.LogHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO : Write a class which handles the menu for categories, that will be the home page.
        //TODO : Refactor this class to be the welcome screen class
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, CategoryFragment.newInstance())
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogHelper.log(TAG, "debug", "onStart() called");
    }
}
