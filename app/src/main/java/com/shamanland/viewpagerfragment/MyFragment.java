package com.shamanland.viewpagerfragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyFragment extends Fragment implements ViewPagerFragment {
    private boolean mSelected;

    public static MyFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);

        MyFragment result = new MyFragment();
        result.setArguments(args);
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView result = new TextView(inflater.getContext());
        result.setText("Position: " + getPosition());
        return result;
    }

    private int getPosition() {
        return getArguments().getInt("position");
    }

    @Override
    public void onSelected() {
        mSelected = true;
        start();
    }

    @Override
    public void onDeselected() {
        mSelected = false;
    }

    private void start() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Activity activity = getActivity();
                if (activity == null) {
                    return;
                }

                if (!mSelected) {
                    Toast.makeText(activity, "Fragment #" + getPosition() + " stopped", Toast.LENGTH_SHORT).show();
                    return;
                }

                TextView textView = (TextView) activity.findViewById(R.id.text);
                if (textView != null) {
                    textView.setText("Fragment #" + getPosition() + " works: " + System.nanoTime() % 10000);
                }

                handler.postDelayed(this, 150);
            }
        }, 150);
    }
}
