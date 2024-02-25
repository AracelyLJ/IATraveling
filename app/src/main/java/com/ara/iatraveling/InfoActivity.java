package com.ara.iatraveling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ara.models.City;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_city_name;
    private ImageView img_photo_city;

    private CardView btn_places_to_visit, btn_food, btn_history, btn_map;
    City city;
    String city_name;

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

        city = new City();
        city_name = "cdmx";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String strImage = extras.getString("imageUri");
            city_name = extras.getString("city_name");
            Uri uriImage = Uri.parse(strImage);
            tv_city_name.setText(city_name);
            img_photo_city.setImageURI(uriImage);
            getDatabase();

        }



    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_places_to_visit) {
            showInfo(city.getVisit());
        }  else if (id == R.id.btn_food) {
            showInfo(city.getFood());
        } else if (id == R.id.btn_history) {
            showInfo(city.getHistory());
        } else if (id == R.id.btn_map) {
            Intent intent = new Intent(InfoActivity.this, MapActivity.class);
            intent.putExtra("coordenates",city.getMap());
            startActivity(intent);
        }
    }

    public void getDatabase() {
        FirebaseDatabase.getInstance().
                getReference("cities")
                .child(city_name)
                .get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        String food = Objects.requireNonNull(dataSnapshot.child("food").getValue()).toString();
                        String history = Objects.requireNonNull(dataSnapshot.child("history").getValue()).toString();
                        String map = Objects.requireNonNull(dataSnapshot.child("map").getValue()).toString();
                        String title = Objects.requireNonNull(dataSnapshot.child("title").getValue()).toString();
                        String visit = Objects.requireNonNull(dataSnapshot.child("visit").getValue()).toString();
//                        String map = "39.9075, 116.39723";
                        city = new City(food, history, map, title, visit);
                        tv_city_name.setText(city.getTitle());
                    }
                });
    }

    public void showInfo(String info) {
        Dialog dialog = new Dialog(InfoActivity.this);
        //se asigna el layout
        dialog.setContentView(R.layout.cardview_dialog);
        TextView tv_city_title = dialog.findViewById(R.id.tv_city_title);
        TextView tv_info = dialog.findViewById(R.id.tv_info);
        ImageView imgCloseDialog = dialog.findViewById(R.id.imgCloseDialog);

        tv_city_title.setText(city.getTitle().toUpperCase());
        tv_info.setText(info.replace("**","\\\n\\\n").replace("\\",""));
        imgCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}