package com.hassanmorel.bikex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hassanmorel.bikex.api.ApiClient;
import com.hassanmorel.bikex.api.ApiInterface;
import com.hassanmorel.bikex.api.models.ApiFeatureLive;

import retrofit2.Call;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    ImageView img = (ImageView) findViewById(R.id.imageViewDetail);

    TextView txtId = (TextView) findViewById(R.id.textIdFeature);
    TextView txtAddr = (TextView) findViewById(R.id.textAddrFeature);

    TextView txtHour = (TextView) findViewById(R.id.textHourCount);
    TextView txtDay = (TextView) findViewById(R.id.textDayCount);
    TextView txtYear = (TextView) findViewById(R.id.textYearcount);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        txtId.setText(id);
        String addr = intent.getStringExtra("addr");
        txtAddr.setText(addr);




        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiFeatureLive> call = apiService.getData(id);

        call.enqueue(new retrofit2.Callback<ApiFeatureLive>() {
            @Override
            public void onResponse(@NonNull Call<ApiFeatureLive> call, @NonNull Response<ApiFeatureLive> response) {

                assert response.body() != null;

                txtHour.setText(response.body().getData().getHourCount());
                txtDay.setText(response.body().getData().getDayCount());
                txtYear.setText(response.body().getData().getYearCount());
            }

            @Override
            public void onFailure(@NonNull Call<ApiFeatureLive> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}