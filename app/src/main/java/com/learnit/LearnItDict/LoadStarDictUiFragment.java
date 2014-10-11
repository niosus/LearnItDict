package com.learnit.LearnItDict;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dd.CircularProgressButton;

public class LoadStarDictUiFragment extends Fragment{
    private static String LOG_TAG = "my_logs";
    TextView _explain;
    TextView _deleteTheApp;
    CircularProgressButton _button;
    private OnUserAction mCallback;

    public interface OnUserAction {
        public void onButtonPressed();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (MainActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnUserAction");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main, container, false);
        _explain = (TextView) v.findViewById(R.id.txtExplanation);
        _deleteTheApp = (TextView) v.findViewById(R.id.txtDelete);
        _button = (CircularProgressButton) v.findViewById(R.id.circularProgressButton);

        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onButtonPressed();
            }
        });
        Log.e(LOG_TAG, "everything was created");
        init();
        return v;
    }

    private void init()
    {
        try
        {
            _deleteTheApp.setVisibility(View.INVISIBLE);
            _button.setVisibility(View.VISIBLE);
            _explain.setVisibility(View.VISIBLE);
        }
        catch (NullPointerException e)
        {
           Log.e("my_logs", "something went wrong in init()");
        }
    }

    public void showInitialLayout()
    {
        init();
        _button.setIndeterminateProgressMode(true);
        _explain.setVisibility(View.VISIBLE);
        _button.setVisibility(View.VISIBLE);
    }

    public void showWorkingLayout()
    {
        init();
    }

    public void showDoneLayout()
    {
        init();
        _deleteTheApp.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void flyAwayDown(final View v)
    {
        Animation anim = AnimationUtils.loadAnimation(this.getActivity(), R.anim.fly_away_down);
        v.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void flyAwayUp(final View v)
    {
        Animation anim = AnimationUtils.loadAnimation(this.getActivity(), R.anim.fly_away_up);
        v.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void flyInFromBelow(final View v)
    {
        Animation anim = AnimationUtils.loadAnimation(this.getActivity(), R.anim.fly_in_from_below);
        v.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void playFadeOutAnimation(final View v)
    {
        Animation anim = AnimationUtils.loadAnimation(this.getActivity(), R.anim.fade_out);
        v.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setAlpha(0.3f);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void setProgressVisible()
    {
        _button.setProgress(50);
    }

    public void showSuccess()
    {
        _button.setProgress(100);
        flyInFromBelow(_deleteTheApp);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
