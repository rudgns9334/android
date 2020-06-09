package com.example.firebasetest;


import android.os.Bundle;
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

import static com.example.firebasetest.SubActivity.calledAlready;

public class RecommendList extends AppCompatActivity {
    String name;
    String image_url;
    ArrayList<String> material_list = new ArrayList<String>();
    ArrayList<Data> Data_list;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.recommendlist);
        getSupportActionBar().setTitle("추천 레시피 !");

        final DBHelper2 dbHelper2 = new DBHelper2(getApplicationContext(), "Recommend.db", null, 1);
        final TextView result = (TextView) findViewById(R.id.result);

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference dbR = db.getReference();
        ArrayList<Integer> R_id_list = dbHelper2.getR_id_list();
        ArrayList<RecipeComponent> RecipeList = new ArrayList<RecipeComponent>();
        for(Integer R_id:R_id_list) {
            Query recipy = dbR.child("recipy").child("data").orderByChild("RECIPE_ID").equalTo(R_id);
            final Query material = dbR.child("material").child("data").orderByChild("RECIPE_ID").equalTo(R_id);
            recipy.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot data : dataSnapshot.getChildren()) {
                        name = data.child("RECIPE_NM_KO").getValue(String.class);
                        image_url = data.child("IMG_URL").getValue(String.class);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            material.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot data : dataSnapshot.getChildren()) {
                        String a =data.child("IRDNT_NM").getValue(String.class);
                        material_list.add(a);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            RecipeList.add(new RecipeComponent(R_id, name, image_url, material_list));
        }
        Data_list = new ArrayList<Data>();
        for(RecipeComponent element: RecipeList){
            Data_list.add(new Data(element.image_url, element.name, element.material));
        }
    }
}
