package com.example.firebasetest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static com.example.firebasetest.SubActivity.calledAlready;

public class Recipe extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflater =  LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.actionbartitle, null);
        ((TextView)v.findViewById(R.id.title)).setText("요리 레시피 !");
        getSupportActionBar().setCustomView(v);

        TextView name = findViewById(R.id.name);
        TextView material = findViewById(R.id.material);
        TextView cooking = findViewById(R.id.cooking);
        ImageView image = findViewById(R.id.image);


        String rname = getIntent().getStringExtra("name");
        String image_url = getIntent().getStringExtra("image_url");
        String rmaterial = getIntent().getStringExtra("material");
        String rcooking = getIntent().getStringExtra("cooking");

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference dbR = db.getReference();



        Glide.with(this).load(image_url).into(image);
        name.setText(rname);
        material.setText(rmaterial);
        cooking.setText(rcooking);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
