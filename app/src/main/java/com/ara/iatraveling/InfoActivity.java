package com.ara.iatraveling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ara.models.MessageService;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_city_name;
    private ImageView img_photo_city;

    private CardView btn_places_to_visit, btn_food, btn_history, btn_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tv_city_name = findViewById(R.id.tv_city_name);
        img_photo_city = findViewById(R.id.img_photo_city);
        btn_places_to_visit = findViewById(R.id.btn_places_to_visit);
        btn_food = findViewById(R.id.btn_food);
        btn_history = findViewById(R.id.btn_history);
        btn_map = findViewById(R.id.btn_map);

        btn_places_to_visit.setOnClickListener(this);
        btn_food.setOnClickListener(this);
        btn_history.setOnClickListener(this);
        btn_map.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String strImage = extras.getString("imageUri");
            String city_name = extras.getString("city_name");
            Uri uriImage = Uri.parse(strImage);
            tv_city_name.setText(city_name);
            img_photo_city.setImageURI(uriImage);
        }



    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_places_to_visit) {
            Toast.makeText(this, "PLACES", Toast.LENGTH_SHORT).show();
        }  else if (id == R.id.btn_food) {
            Toast.makeText(this, "FOOD", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btn_history) {
            Toast.makeText(this, "HISTORY", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btn_map) {
            Intent intent = new Intent(InfoActivity.this, MapActivity.class);
            startActivity(intent);
        }
    }

    public void postData() {
        String API_ROUTE = "https://us-central1-aiplatform.googleapis.com/v1/projects/aitraveling" +
                "/locations/us-central1/publishers/google/models/gemini-1.0-pro:streamGenerateContent?alt=sse";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ROUTE)
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();

        MessageService messageService = retrofit.create(MessageService.class);
    }
}