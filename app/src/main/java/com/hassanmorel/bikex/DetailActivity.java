package com.hassanmorel.bikex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.hassanmorel.bikex.api.ApiClient;
import com.hassanmorel.bikex.api.ApiInterface;
import com.hassanmorel.bikex.api.models.ApiFeatureLive;

import retrofit2.Call;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgView;
    TextView txtId;
    TextView addressView;

    TextView txtHour;
    TextView txtDay;
    TextView txtYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        imgView = findViewById(R.id.imageViewDetail);

        txtId = findViewById(R.id.textIdFeature);
        addressView = findViewById(R.id.textAddrFeature);
        txtHour = findViewById(R.id.textHourCount);
        txtDay = findViewById(R.id.textDayCount);
        txtYear = findViewById(R.id.textYearcount);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        txtId.setText(id);
        String roadEn = intent.getStringExtra("address");
        addressView.setText(roadEn);
        String imgUrl = intent.getStringExtra("img");

        Glide.with(this)
                .load(imgUrl)
                .centerCrop()
                .into(imgView);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.getData(id).subscribe(apiFeatureLive -> {
            txtDay.setText("Day count : " + apiFeatureLive.getData().getDayCount());
            txtHour.setText("Hour count : " + apiFeatureLive.getData().getHourCount());
            txtYear.setText("Year count : " + apiFeatureLive.getData().getYearCount());
        });
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}