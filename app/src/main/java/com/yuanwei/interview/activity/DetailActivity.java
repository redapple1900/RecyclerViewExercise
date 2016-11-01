package com.yuanwei.interview.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.yuanwei.interview.R;
import com.yuanwei.interview.fragment.DetailFragment;

public class DetailActivity extends FragmentActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      DetailFragment fragment = new DetailFragment();

      fragment.setArguments(getIntent().getExtras());

      getSupportFragmentManager().beginTransaction()
          .replace(R.id.container, fragment)
          .commit();
    }
  }
}
