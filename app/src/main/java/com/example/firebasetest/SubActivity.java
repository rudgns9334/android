
package com.example.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubActivity extends AppCompatActivity {

    private ListView listview;
    List mat = new ArrayList<>();
    ArrayAdapter adapter;
    static boolean calledAlready = false;
    public Button bts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sub);
/*
        bts = (Button) findViewById(R.id.back);
        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
*/
        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }
    /*
        listview = (ListView) findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(this, R.layout.activity_list, mat);
        listview.setAdapter(adapter);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference dbR = db.getReference();

        dbR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot recipe = dataSnapshot.child("recipy").child("data");
                DataSnapshot material = dataSnapshot.child("material").child("data");

                int count = 0;
                for (DataSnapshot re : recipe.getChildren()) {
                    int m_count = 0;
                    HashMap<String,Object> mat_count =new HashMap<>();
                    Integer R_rcode = re.child("RECIPE_ID").getValue(Integer.class);
                    String R = Integer.toString(R_rcode);
                    count++;
                    mat.add(count);

                    for (DataSnapshot ma : material.getChildren()) {
                        Integer M_rcode = ma.child("RECIPE_ID").getValue(Integer.class);
                        String M = Integer.toString(M_rcode);

                        if (M.equals(R)) {
                            m_count++;
                            String str2 = ma.child("IRDNT_NM").getValue(String.class);
                            mat.add(str2);
                        }
                    }
/*
                    String a = re.child("CALORIE").getValue(String.class);
                    String b = re.child("COOKING_TIME").getValue(String.class);
                    String c = re.child("DET_URL").getValue(String.class);
                    String d = re.child("IMG_URL").getValue(String.class);
                    String e = re.child("IRDNT_CODE").getValue(String.class);
                    String f = re.child("LEVEL_NM").getValue(String.class);
                    String g = re.child("NATION_CODE").getValue(String.class);
                    String h = re.child("NATION_NM").getValue(String.class);
                    String i = re.child("PC_NM").getValue(String.class);
                    String j = re.child("QNT").getValue(String.class);
                    Integer k = re.child("RECIPE_ID").getValue(Integer.class);
                    String l = re.child("RECIPE_NM_KO").getValue(String.class);
                    Integer m = re.child("RN").getValue(Integer.class);
                    String n = re.child("SUMRY").getValue(String.class);
                    String o = re.child("TY_CODE").getValue(String.class);
                    String p = re.child("TY_NM").getValue(String.class);
                    mat_count.put("CALORIE",a);
                    mat_count.put("COOKING_TIME",b);
                    mat_count.put("DET_URL",c);
                    mat_count.put("IMG_URL",d);
                    mat_count.put("IRDNT_CODE",e);
                    mat_count.put("LEVEL_NM",f);
                    mat_count.put("NATION_CODE",g);
                    mat_count.put("NATION_NM",h);
                    mat_count.put("PC_NM",i);
                    mat_count.put("QNT",j);
                    mat_count.put("RECIPE_NM_KO",l);
                    mat_count.put("SUMRY",n);
                    mat_count.put("TY_CODE",o);
                    mat_count.put("TY_NM",p);
                    mat_count.put("RECIPE_ID",k);
                    mat_count.put("RN",m);
                    mat_count.put("MATERIAL_CNT",m_count);


                    //mat_count.put("MATERIAL_CNT",m_count);
                    //re.getRef().setValue(mat_count);
                    mat.add(" ");

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}