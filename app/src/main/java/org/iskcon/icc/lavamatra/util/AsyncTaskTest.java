package org.iskcon.icc.lavamatra.util;

import android.os.AsyncTask;

/**
 * Created by Ankush on 24-05-2017.
 */

public class AsyncTaskTest extends AsyncTask<Integer, Integer, Integer> {
    private static final String TAG = AsyncTaskTest.class.getSimpleName();

    @Override
    protected Integer doInBackground(Integer... integer) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
        LogHelper.log(TAG, "debug", "doInBackground() finishing");
        return 0;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        LogHelper.log(TAG, "debug", "onPreExecute() called");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        LogHelper.log(TAG, "debug", "onProgressUpdate() called");
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        LogHelper.log(TAG, "debug", "onPostExecute() called");
    }
}
