package com.yuanwei.interview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yuanwei.interview.fragment.ListFragment;
import com.yuanwei.interview.R;

public class MainActivity extends AppCompatActivity {
    private static final String FRAGMENT_TAG = "wishlist_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragmentIfNeeded();
    }

    private void showFragmentIfNeeded() {
        if (getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new ListFragment(), FRAGMENT_TAG)
                    .commit();
        }
    }
}
