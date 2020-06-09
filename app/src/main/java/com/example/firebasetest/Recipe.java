package com.example.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.firebasetest.SubActivity.calledAlready;

public class Recipe extends AppCompatActivity{

    List cook_dc = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_test);

        TextView name = (TextView) findViewById(R.id.name);
        TextView cooking = (TextView) findViewById(R.id.cooking);
        ImageView image = findViewById(R.id.image);

        Intent intent = getIntent();
        int R_id = Integer.parseInt(intent.getStringExtra("R_id"));
        RecipeComponent recipecomponent = (RecipeComponent)intent.getSerializableExtra("class");

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference dbR = db.getReference();


        Query recipe = dbR.child("cooking").child("data").orderByChild("RECIPE_ID").equalTo(R_id);
        recipe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot order: dataSnapshot.getChildren()){
                    String cook_order = order.child("COOKING_DC").getValue(String.class);
                    cook_dc.add(cook_order);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Glide.with(this).load(recipecomponent.url_image).into(image);
        name.setText(recipecomponent.name);
        cooking.setText((CharSequence) cook_dc);
    }
}
