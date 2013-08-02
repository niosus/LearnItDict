package com.learnit.LearnItDict;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyActivity extends Activity
        implements LoadStarDictWorker.OnTaskActionListener, LoadStarDictUiFragment.OnUserAction{
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
    public void onStartLoading() {
        _uiFragment.setProgressVisible();
    }

    @Override
    public void onProgressUpdate(int progress) {

    }

    @Override
    public void onDictLoaded() {
        _uiFragment.setProgressInvisible();
        _uiFragment.showSuccess();
    }

    @Override
    public void onButtonPressed() {
        _taskFragment.executeTask();
    }
}
