package com.yuanwei.interview.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yuanwei.interview.Constants;
import com.yuanwei.interview.R;
import com.yuanwei.interview.activity.DetailActivity;
import com.yuanwei.interview.adapter.ListAdapter;
import com.yuanwei.interview.models.GeneralModel;
import com.yuanwei.interview.network.NetworkClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ListFragment extends Fragment implements ListAdapter.OnItemResponseListener {

  private ListAdapter mListAdapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setRetainInstance(true);

    mListAdapter = new ListAdapter();

    mListAdapter.setOnItemResponseListener(this);

    fetchWishlist();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_filter, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.price_filter:
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.list_fragment_layout, container, false);

    RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

    /*
    ((LinearLayoutManager) recyclerView.getLayoutManager()).setOrientation(
        getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ?
            LinearLayoutManager.VERTICAL :
            LinearLayoutManager.HORIZONTAL);
    */
    ((GridLayoutManager) recyclerView.getLayoutManager()).setOrientation(
        getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ?
            GridLayoutManager.VERTICAL :
            GridLayoutManager.HORIZONTAL);

    recyclerView.setAdapter(mListAdapter);

    setHasOptionsMenu(true);

    return v;
  }

  private void fetchWishlist() {

    NetworkClient.getModels("37.4242", "-122.1381", new Callback<List<GeneralModel>>() {

      @Override
      public void onResponse(Call<List<GeneralModel>> call, Response<List<GeneralModel>> response) {
        mListAdapter.setListings(response.body());
      }

      @Override
      public void onFailure(Call<List<GeneralModel>> call, Throwable t) {
        Log.d(getClass().getSimpleName(), toString());
      }
    });
  }

  @Override
  public void onResponse(View view, int adapterPosition) {
    GeneralModel model = mListAdapter.getListings().get(adapterPosition);

    Bundle bundle = new Bundle();
    bundle.putString(Constants.sPhotoUrl, model.getImageUrl());
    bundle.putString(Constants.sName, model.getName());
    bundle.putString(Constants.sDescription, model.getDescription());

    Intent intent = new Intent(getActivity(), DetailActivity.class);
    intent.putExtras(bundle);

    // BEGIN_INCLUDE(start_activity)
    /**
     * Now create an {@link android.app.ActivityOptions} instance using the
     * {@link ActivityOptionsCompat#makeSceneTransitionAnimation(Activity, Pair[])} factory
     * method.
     */
    ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
        getActivity(),
        // Now we provide a list of Pair items which contain the view we can transitioning
        // from, and the name of the view it is transitioning to, in the launched activity
        new Pair<>(view.findViewById(R.id.image), DetailFragment.VIEW_NAME_HEADER_IMAGE),
        new Pair<>(view.findViewById(R.id.title), DetailFragment.VIEW_NAME_HEADER_TITLE));

    // Now we can start the Activity, providing the activity options as a bundle
    ActivityCompat.startActivity(getActivity(), intent, activityOptions.toBundle());
    // END_INCLUDE(start_activity)
  }
}
