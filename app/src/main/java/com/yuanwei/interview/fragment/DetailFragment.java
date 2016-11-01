package com.yuanwei.interview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yuanwei.interview.Constants;
import com.yuanwei.interview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailFragment extends Fragment {

  // View name of the header image. Used for activity scene transitions
  public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";

  // View name of the header title. Used for activity scene transitions
  public static final String VIEW_NAME_HEADER_TITLE = "detail:header:title";

  @BindView((R.id.image))
  ImageView mImageView;
  @BindView(R.id.name)
  TextView mNameView;
  @BindView(R.id.description) TextView mDescriptionView;
  private Unbinder mUnbinder;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Make sure content are retained after screen rotation
    setRetainInstance(true);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.detail_layout, container, false);
    mUnbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ViewCompat.setTransitionName(mImageView, VIEW_NAME_HEADER_IMAGE);
    ViewCompat.setTransitionName(mNameView, VIEW_NAME_HEADER_TITLE);

    String photoUrl = getArguments().getString(Constants.sPhotoUrl);
    String name = getArguments().getString(Constants.sName);
    String description = getArguments().getString(Constants.sDescription);

    mNameView.setText(name);
    Picasso.with(getActivity()).load(photoUrl).noFade().fit().centerInside().into(mImageView);
    mDescriptionView.setText(description);
  }

  /**
   * Dereference all instances which hold the reference to the activity to avoid memory leak
   */
  @Override
  public void onDestroyView() {
    mUnbinder.unbind();
    super.onDestroyView();
  }

  @Override
  public void onDestroy() {
    Log.d(getClass().getSimpleName(), "onDestroy");
    super.onDestroy();
  }
}
