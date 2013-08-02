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

public class LoadStarDictUiFragment extends Fragment{
    ProgressBar _progressBar;
    Button _button;
    TextView _success;
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
                playCloseAnimation(view);
                mCallback.onButtonPressed();
            }
        });
        _success = (TextView) v.findViewById(R.id.txtSuccess);
        _success.setVisibility(View.INVISIBLE);
        return v;
    }

    private void playCloseAnimation(final View v)
    {
        Animation anim = AnimationUtils.loadAnimation(this.getActivity(), R.anim.fly_away);
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

    private void playOpenAnimation(final View v)
    {
        Animation anim = AnimationUtils.loadAnimation(this.getActivity(), R.anim.fly_in);
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

    public void setProgressVisible()
    {
        playOpenAnimation(_progressBar);
    }

    public void setProgressInvisible()
    {
        playCloseAnimation(_progressBar);
    }

    public void showSuccess()
    {
        playOpenAnimation(_success);
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
