package com.yuanwei.interview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yuanwei.interview.R;
import com.yuanwei.interview.models.GeneralModel;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListingViewHolder> {

  private final List<GeneralModel> dataSet = new ArrayList<>();
  private OnItemResponseListener mOnItemResponseListener;

  public void setOnItemResponseListener(OnItemResponseListener listener) {
    mOnItemResponseListener = listener;
  }

  public void setListings(List<GeneralModel> items) {
    dataSet.clear();
    dataSet.addAll(items);
    notifyDataSetChanged();
  }

  public List<GeneralModel> getListings() {
    return dataSet;
  }

  @Override
  public ListAdapter.ListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//    return new ListingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_item, parent, false));
    return new ListingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false));
  }

  @Override
  public void onBindViewHolder(final ListAdapter.ListingViewHolder holder, int position) {
    GeneralModel model = dataSet.get(position);

    holder.title.setText(model.getName());
    holder.subtitle.setText(model.getDescription());

    Picasso.with(holder.image.getContext())
        .load(model.getImageUrl())
        .fit()
        .centerInside()
        .into(holder.image);

    holder.button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mOnItemResponseListener.onResponse(holder.itemView, holder.getAdapterPosition());
      }
    });
  }

  @Override
  public int getItemCount() {
    return dataSet.size();
  }

  public interface OnItemResponseListener {
    void onResponse(View view, int adapterPosition);
  }

  class ListingViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView subtitle;
    ImageView image;
    Button button;

    public ListingViewHolder(View itemView) {
      super(itemView);
      subtitle = (TextView) itemView.findViewById(R.id.subtitle);
      title = (TextView) itemView.findViewById(R.id.title);

      image = (ImageView) itemView.findViewById(R.id.image);

      button = (Button) itemView.findViewById(R.id.button);
    }
  }
}
