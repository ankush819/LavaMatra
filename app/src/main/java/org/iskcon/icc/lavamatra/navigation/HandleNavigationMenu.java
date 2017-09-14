package org.iskcon.icc.lavamatra.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.iskcon.icc.lavamatra.authentication.LoginActivity;
import org.iskcon.icc.lavamatra.categories.DisplayCategoriesActivity;
import org.iskcon.icc.lavamatra.util.LogHelper;

/**
 * Created by Ankush on 14-09-2017.
 */

public class HandleNavigationMenu {

    private ArrayAdapter<String> navAdapter;
    private static final String TAG = HandleNavigationMenu.class.getSimpleName();

    public void populateNavBar(final Context context, ListView listView) {
        LogHelper.log(TAG, "debug", "Inside population navbar");
        String[] navItems = {"SIGNOUT"};
        navAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, navItems);
        listView.setAdapter(navAdapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogHelper.log(TAG, "debug", "position " + String.valueOf(position) + " was pressed");
                switch (position) {
                    case 0:
                        //SignOut
                        LogHelper.log(TAG, "debug", "Signing out now");
                        Signout.signOut(); //Offloaded the signout activity to another class
                        Intent myIntent = new Intent(view.getContext(), LoginActivity.class);
                        context.startActivity(myIntent);
                        ((Activity) context).finish(); //TODO : Check if i have to actually finish the activity
                }
            }
        });
    }
}
