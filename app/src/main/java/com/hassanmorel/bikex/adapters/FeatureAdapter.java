package com.hassanmorel.bikex.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hassanmorel.bikex.DetailActivity;
import com.hassanmorel.bikex.R;
import com.hassanmorel.bikex.models.Feature;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder> {
    private List<Feature> features;
    private boolean isList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView featureAddressTextView;
        private final ImageView featureImage;

        public ViewHolder(View view) {
            super(view);
            featureAddressTextView = view.findViewById(R.id.feature_address_text);
            featureImage = view.findViewById(R.id.imageView);
        }

        public TextView getFeatureAddressTextView() {
            return featureAddressTextView;
        }

        public ImageView getFeatureImageView() {
            return featureImage;
        }
    }

    public FeatureAdapter() {
        isList = true;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate((!isList) ? R.layout.fragment_grid_fragment : R.layout.feature_preview_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageUrl = features.get(position).getImage();
        String imageId = features.get(position).getId();

        holder.setIsRecyclable(false);
        holder.getFeatureAddressTextView().setText(imageId);
        Glide.with(holder.itemView)
                .load(imageUrl)
                .centerCrop()
                .into(holder.getFeatureImageView());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("id", imageId);
            intent.putExtra("address", features.get(position).getRoadEn());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (features != null) ? features.size() : 0;
    }

    public void setList(boolean list) {
        isList = list;
    }

}
