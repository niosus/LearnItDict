/*
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License. To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/.
 */


package com.learnit.LearnItDict;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class LoadStarDictActivity extends Activity implements LoadStarDictWorker.OnTaskActionListener {
    protected static final String LOG_TAG = "my_logs";
    LoadStarDictUiFragment _uiFragment;
    LoadStarDictWorker _taskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _uiFragment = new LoadStarDictUiFragment();

        FragmentManager fragmentManager = getFragmentManager();

        _taskFragment = (LoadStarDictWorker) fragmentManager
                .findFragmentByTag(LoadStarDictWorker.TAG);
        if (_taskFragment == null)
        {
            _taskFragment = new LoadStarDictWorker();
            fragmentManager.beginTransaction()
                    .add(_taskFragment, LoadStarDictWorker.TAG)
                    .commit();
        }
        fragmentManager.beginTransaction()
                .add(android.R.id.content, _uiFragment)
                .commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (_taskFragment.DONE)
        {
            this.finish();
        }
    }

    @Override
    public void onStartLoading() {
    }

    @Override
    public void onProgressUpdate(int progress) {

    }

    @Override
    public void onDictLoaded() {

    }
}

