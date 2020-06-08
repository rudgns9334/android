package com.example.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.firebasetest.SubActivity.calledAlready;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("나의 냉장고 !");

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Refrigerator.db", null, 1);
        final DBHelper2 dbHelper2 = new DBHelper2(getApplicationContext(), "Recommend.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = (TextView) findViewById(R.id.result);

        final EditText etItem = (EditText) findViewById(R.id.item);

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference dbR = db.getReference();

        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                if(item.equals(""))
                    Toast.makeText(MainActivity.this, "재료를 입력하세요 !", Toast.LENGTH_SHORT).show();
                else if(dbHelper.isEqual(item) == true)
                    Toast.makeText(MainActivity.this, "이미 냉장고 안에 있어요 !", Toast.LENGTH_SHORT).show();
                else {
                    dbHelper.insert(item);
                    Query mitem = dbR.child("material").child("data").orderByChild("IRDNT_NM").equalTo(item);
                    mitem.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot mquery: dataSnapshot.getChildren()){
                                final Integer R_id = mquery.child("RECIPE_ID").getValue(Integer.class);
                                if(dbHelper2.isEqual(R_id)) {
                                    dbHelper2.cntup(R_id);
                                }
                                else{
                                    Query recipy = dbR.child("recipy").child("data").orderByChild("RECIPE_ID").equalTo(R_id);
                                    recipy.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for(DataSnapshot data : dataSnapshot.getChildren()) {
                                                Integer mcount = data.child("MATERIAL_CNT").getValue(Integer.class);
                                                dbHelper2.insert(R_id, mcount);
                                                dbHelper2.cntup(R_id);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                result.setText(dbHelper.getResult()+dbHelper2.getResult());
                etItem.setText(null);
            }
        });


        // DB에 있는 데이터 삭제
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();

                if (dbHelper.isEqual(item) == false)
                    Toast.makeText(MainActivity.this, "방금 입력한 재료는 없어요 !", Toast.LENGTH_SHORT).show();
                else {
                    dbHelper.delete(item);
                    Query mitem = dbR.child("material").child("data").orderByChild("IRDNT_NM").equalTo(item);
                    mitem.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot mquery: dataSnapshot.getChildren()){
                                Integer R_id = mquery.child("RECIPE_ID").getValue(Integer.class);
                                dbHelper2.cntdown(R_id);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                result.setText(dbHelper.getResult()+dbHelper2.getResult());
                etItem.setText(null);
            }
        });

        // DB에 있는 데이터 조회
        Button select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(dbHelper.getResult()+dbHelper2.getResult());
            }
        });

        Button clean = (Button) findViewById(R.id.clean);
        clean.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String all = "ALL";
                dbHelper.delete("ALL");
                result.setText(dbHelper.getResult()+dbHelper2.getResult());
            }
        });




    }
}

