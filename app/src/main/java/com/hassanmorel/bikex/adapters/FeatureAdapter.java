package com.hassanmorel.bikex.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hassanmorel.bikex.R;
import com.hassanmorel.bikex.models.Feature;
import com.hassanmorel.bikex.viewmodels.FeatureViewModel;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.http.Url;


public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.ViewHolder> {
    private List<Feature> features;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView featureAddressTextView;
        private final TextView featureDayCountTextView;
        private final ImageView featureImage;

        public ViewHolder(View view) {
            super(view);
            featureAddressTextView = (TextView) view.findViewById(R.id.feature_address_text);
            featureDayCountTextView = (TextView) view.findViewById(R.id.feature_day_count_text);
            featureImage = (ImageView) view.findViewById(R.id.imageView);
        }

        public TextView getFeatureAddressTextView() {
            return featureAddressTextView;
        }

        public TextView getFeatureDayCountTextView() {
            return featureDayCountTextView;
        }

        public ImageView getFeatureImageView() {
            return featureImage;
        }
    }

    public FeatureAdapter() {

    }

    public void setFeatures(List<Feature> features) {

        this.features = features;
        notifyDataSetChanged();
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
        holder.getFeatureAddressTextView().setText(features.get(position).getId());
        new DownloadImageTask((ImageView) holder.getFeatureImageView()).execute(features.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return (features!=null)?features.size():0;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setMaxHeight(500);
            bmImage.setMaxWidth(500);
            bmImage.setImageBitmap(result);

        }
    }

}
