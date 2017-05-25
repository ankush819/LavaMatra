package org.iskcon.icc.lavamatra.util;

import android.os.AsyncTask;

/**
 * Created by Ankush on 24-05-2017.
 */

public class AsyncTaskTest extends AsyncTask<String, Void, String> {
    private static final String TAG = AsyncTaskTest.class.getSimpleName();

    @Override
    protected String doInBackground(String... integer) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
        LogHelper.log(TAG, "debug", "doInBackground() finishing");
        return "0";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        LogHelper.log(TAG, "debug", "onPreExecute() called");
    }


    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        LogHelper.log(TAG, "debug", "onPostExecute() called");
    }
}
