package org.iskcon.icc.lavamatra.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.iskcon.icc.lavamatra.Model.MediaModel;
import org.iskcon.icc.lavamatra.R;
import org.iskcon.icc.lavamatra.categories.adapter.CategoryListRecyclerAdapter;
import org.iskcon.icc.lavamatra.util.AsyncTaskTest;
import org.iskcon.icc.lavamatra.util.LogHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ankush on 24-05-2017.
 */

public class CategoryFragment extends Fragment {

    private static final String TAG = CategoryFragment.class.getSimpleName();
    private ArrayList<MediaModel> arrayList;
    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        LogHelper.log(TAG, "debug", "onCreateView() called");

        //TODO 1 : Inflate a simple view which contains a list of all the categories
        //TODO 2 : Query the model to get the list of media and send it to adapters to set it in the view
        //TODO 3 : Set onClickListeners
        View rootView = inflater.inflate(R.layout.adapter_test, container, false);
        arrayList = new ArrayList<>();

        RecyclerView adapterTestListView = (RecyclerView) rootView.findViewById(R.id.adapterTest);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        adapterTestListView.setLayoutManager(layoutManager);
        CategoryListRecyclerAdapter categoryListRecyclerAdapter =
                new CategoryListRecyclerAdapter(getContext(), arrayList);
        adapterTestListView.setAdapter(categoryListRecyclerAdapter);

        //AsyncTaskTest asyncTaskTest = new AsyncTaskTest();
        //asyncTaskTest.execute(1, 2, 3);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogHelper.log(TAG, "info", "onActivityStarted() called");
        arrayList.add(new MediaModel());
        arrayList.add(new MediaModel());
    }
}
