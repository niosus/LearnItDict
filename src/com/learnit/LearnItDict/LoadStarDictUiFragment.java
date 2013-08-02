package com.learnit.LearnItDict;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadStarDictUiFragment extends Fragment{
    ProgressBar _progressBar;
    Button _button;
    TextView _success;
    TextView _welcome;
    TextView _explain;
    TextView _copying;
    TextView _deleteTheApp;
    private OnUserAction mCallback;

    public interface OnUserAction {
        public void onButtonPressed();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (MyActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnUserAction");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main, container, false);
        _progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        _progressBar.setIndeterminate(true);
        _progressBar.setVisibility(View.INVISIBLE);

        _button = (Button) v.findViewById(R.id.btnStart);
        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flyAwayDown(view);
                mCallback.onButtonPressed();
            }
        });
        _success = (TextView) v.findViewById(R.id.txtSuccess);
        _success.setVisibility(View.INVISIBLE);

        _welcome = (TextView) v.findViewById(R.id.txtWelcome);
        _explain = (TextView) v.findViewById(R.id.txtExplanation);
        _copying = (TextView) v.findViewById(R.id.txtCopying);
        _copying.setVisibility(View.INVISIBLE);
        _deleteTheApp = (TextView) v.findViewById(R.id.txtDelete);
        _deleteTheApp.setVisibility(View.INVISIBLE);
        return v;
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

    private void flyInFromAbove(final View v)
    {
        Animation anim = AnimationUtils.loadAnimation(this.getActivity(), R.anim.fly_in_from_above);
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
        flyInFromBelow(_progressBar);
        flyAwayUp(_welcome);
        flyAwayUp(_explain);
        _copying.postDelayed(new Runnable() {
            @Override
            public void run() {
                flyInFromAbove(_copying);
            }
        }, 500);
    }

    public void setProgressInvisible()
    {
        flyAwayDown(_progressBar);
    }

    public void showSuccess()
    {
        flyInFromBelow(_success);
        flyAwayUp(_copying);
        _deleteTheApp.postDelayed(new Runnable() {
            @Override
            public void run() {
                flyInFromAbove(_deleteTheApp);
            }
        }, 400);
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
