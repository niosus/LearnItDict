package com.learnit.LearnItDict;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoadStarDictWorker extends Fragment {
    final String LOG_TAG = "my_logs";
    public static String TAG = "task_fragment";
    public boolean DONE = false;
    private static GetDictTask _task;
    protected Context _context;
    private OnTaskActionListener mCallback;

    // Container Activity must implement this interface
    public interface OnTaskActionListener {
        public void onStartLoading();
        public void onProgressUpdate(int progress);
        public void onDictLoaded();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        _context = activity;
        try {
            mCallback = (MyActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnTaskActionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.e(LOG_TAG, "task was executed");
    }

    public void executeTask()
    {
        updateTask();
        _task.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    private void updateTask()
    {
        if (_task == null || DONE)
        {
            _task = new GetDictTask();
        }
    }


    public class GetDictTask extends AsyncTask<Void, Integer, Void> {

        private GetDictTask()
        {

        }

        protected void copyDict()
        {
            String path = "de-ru";
            AssetManager assetManager = _context.getAssets();
            try {
                String files[] = assetManager.list(path);
                for (String fileName:files)
                {
                    InputStream in;
                    OutputStream out;
                    try {
                        in = assetManager.open(path + File.separator + fileName);
                        File sdPath = Environment.getExternalStorageDirectory();
                        File folder = new File(sdPath, path);
                        folder.mkdirs();
                        File outFile = new File(folder, fileName);
                        Log.e("my_logs", "saving " + outFile.getPath());
                        out = new FileOutputStream(outFile);
                        copyFile(in, out);
                        in.close();
                        out.flush();
                        out.close();
                    } catch(IOException e) {
                        Log.e("tag", "Failed to copy asset file: " + fileName, e);
                    }
                }
            } catch (IOException e) {
                Log.e("my_logs", "can't list" + path);
            }
        }

        private void copyFile(InputStream in, OutputStream out) throws IOException {
            byte[] buffer = new byte[1024];
            int read;
            while((read = in.read(buffer)) != -1){
                out.write(buffer, 0, read);
            }
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            DONE = true;
            _task = null;
            mCallback.onDictLoaded();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            mCallback.onProgressUpdate(progress[0]);
        }

        @Override
        protected Void doInBackground(Void... unused) {
            ((Activity)_context).runOnUiThread(onStartLoading);
            copyDict();
            return null;
        }

        private Runnable onStartLoading = new Runnable() {
            @Override
            public void run() {
                mCallback.onStartLoading();
            }
        };
    }

}
