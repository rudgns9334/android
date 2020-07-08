package com.example.firebasetest;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.firebasetest.SubActivity.calledAlready;

public class RecommendList extends BaseActivity {
    String name;
    String image_url;
    String material_list;
    String cooking;
    ArrayList<Data> Data_list = new ArrayList<>();
    DBHelper2 dbHelper2;
    FirebaseDatabase db;
    DatabaseReference dbR;
    MyAdapter myAdapter;
    ArrayList<Integer> R_id_list;
    Button go;
    ListView listView;
    int i = 0;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.recommendlist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflater =  LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.actionbartitle, null);
        ((TextView)v.findViewById(R.id.title)).setText("추천 레시피 !");
        getSupportActionBar().setCustomView(v);
        listView = findViewById(R.id.listView);
        myAdapter = new MyAdapter(RecommendList.this, Data_list);
        listView.setAdapter(myAdapter);
        dbHelper2 = new DBHelper2(getApplicationContext(), "Recommend.db", null, 1);

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        db = FirebaseDatabase.getInstance();
        dbR = db.getReference();

        progressON("요리중...");
        A thread = new A();
        thread.start();
        synchronized (thread){
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Recipe.class);
                Data data = myAdapter.getItem(position);
                intent.putExtra("name", data.getName());
                intent.putExtra("image_url", data.getImage());
                intent.putExtra("material", data.getMaterial());
                intent.putExtra("cooking", data.getCooking());
                startActivity(intent);
            }
        });
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
    class A extends Thread{

        @Override
        public void run() {
            synchronized (this) {
                R_id_list = dbHelper2.getR_id_list();
                i = 0;
                for (Integer R_id : R_id_list) {
                    Query recipy = dbR.child("recipy").child("data").orderByChild("RECIPE_ID").equalTo(R_id);
                    final Query material = dbR.child("material").child("data").orderByChild("RECIPE_ID").equalTo(R_id);
                    recipy.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                name = data.child("RECIPE_NM_KO").getValue(String.class);
                                image_url = data.child("IMG_URL").getValue(String.class);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Query recipe = dbR.child("cooking").child("data").orderByChild("RECIPE_ID").equalTo(R_id);
                    recipe.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            cooking = "";
                            for (DataSnapshot order : dataSnapshot.getChildren()) {
                                String cook_order = order.child("COOKING_DC").getValue(String.class);
                                Integer cook_num = order.child("COOKING_NO").getValue(Integer.class);
                                String num = "";
                                switch (cook_num) {
                                    case 1:
                                        num = "①";
                                        break;
                                    case 2:
                                        num = "②";
                                        break;
                                    case 3:
                                        num = "③";
                                        break;
                                    case 4:
                                        num = "④";
                                        break;
                                    case 5:
                                        num = "⑤";
                                        break;
                                    case 6:
                                        num = "⑥";
                                        break;
                                    case 7:
                                        num = "⑦";
                                        break;
                                    case 8:
                                        num = "⑧";
                                        break;
                                    case 9:
                                        num = "⑨";
                                        break;
                                    case 10:
                                        num = "⑩";
                                        break;
                                    case 11:
                                        num = "⑪";
                                        break;
                                    case 12:
                                        num = "⑫";
                                        break;
                                    case 13:
                                        num = "⑬";
                                        break;
                                    case 14:
                                        num = "⑭";
                                        break;
                                    case 15:
                                        num = "⑮";
                                        break;
                                }

                                cooking += num + " " + cook_order + "\n";
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    material.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            material_list = "";
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                String a = data.child("IRDNT_NM").getValue(String.class);
                                material_list += a + ",";
                            }
                            material_list = material_list.substring(0, material_list.length() - 1);
                            Data_list.add(new Data(image_url, name, material_list, cooking));
                            myAdapter.notifyDataSetChanged();
                            i++;
                            if(R_id_list.size() == i)
                                progressOFF();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                notify();
            }
        }
    }
}
