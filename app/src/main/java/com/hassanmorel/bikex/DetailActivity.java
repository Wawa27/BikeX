package com.hassanmorel.bikex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtId;
    TextView addressView;

    TextView txtHour;
    TextView txtDay;
    TextView txtYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

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
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}