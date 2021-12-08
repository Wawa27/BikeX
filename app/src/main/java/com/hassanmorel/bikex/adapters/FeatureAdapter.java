package com.hassanmorel.bikex.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hassanmorel.bikex.R;
import com.hassanmorel.bikex.viewmodels.FeatureViewModel;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder> {
    private FeatureViewModel featureViewModel;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView featureAddressTextView;
        private final TextView featureDayCountTextView;

        public ViewHolder(View view) {
            super(view);
            featureAddressTextView = (TextView) view.findViewById(R.id.feature_address_text);
            featureDayCountTextView = (TextView) view.findViewById(R.id.feature_day_count_text);
        }

        public TextView getFeatureAddressTextView() {
            return featureAddressTextView;
        }

        public TextView getFeatureDayCountTextView() {
            return featureDayCountTextView;
        }
    }

    public FeatureAdapter(FeatureViewModel featureViewModel) {
        this.featureViewModel = featureViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feature_preview_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getFeatureAddressTextView().setText(featureViewModel.getFeature(position).getId());
    }

    @Override
    public int getItemCount() {
        return featureViewModel.getFeaturesCount();
    }
}