package com.example.firebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;

import static com.example.firebasetest.SubActivity.calledAlready;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<>();
    ListView listview;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflater =  LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.actionbartitle, null);
        ((TextView)v.findViewById(R.id.title)).setText("나의 냉장고 !");
        getSupportActionBar().setCustomView(v);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Refrigerator.db", null, 1);
        final DBHelper2 dbHelper2 = new DBHelper2(getApplicationContext(), "Recommend.db", null, 1);
        dbHelper.getResult(items);
        listview = (ListView) this.findViewById(R.id.listview_ref) ;
        adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_multiple_choice, items) ;
        listview.setAdapter(adapter) ;
        adapter.notifyDataSetChanged();
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String strText = (String) parent.getItemAtPosition(position);
//            }
//        });
        // 테이블에 있는 모든 데이터 출력
        //final TextView result = findViewById(R.id.result);

        final EditText etItem = findViewById(R.id.item);

        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference dbR = db.getReference();

        Button insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                if(item.equals(""))
                    Toast.makeText(MainActivity.this, "재료를 입력하세요 !", Toast.LENGTH_SHORT).show();
                else if(dbHelper.isEqual(item))
                    Toast.makeText(MainActivity.this, "이미 냉장고 안에 있어요 !", Toast.LENGTH_SHORT).show();
                else {
                    dbHelper.insert(item);
                    items.add(item);
                    System.out.println(items);
                    adapter.notifyDataSetChanged();
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

                etItem.setText(null);
            }
        });


        // DB에 있는 데이터 삭제
        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = "";
                SparseBooleanArray checkbox = listview.getCheckedItemPositions();

                int count = adapter.getCount() ;
                int check_cnt=0;
                for (int i = count-1; i >= 0; i--) {
                    if (checkbox.get(i)) {
                        item = items.get(i);
                        dbHelper.delete(item);
                        items.remove(i);
                        check_cnt++;
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
                }
                if(check_cnt == 0)
                    Toast.makeText(MainActivity.this, "삭제할 재료를 선택해주세요 !", Toast.LENGTH_SHORT).show();
                listview.clearChoices() ;
                adapter.notifyDataSetChanged();

                etItem.setText(null);
            }
        });

        Button clean = findViewById(R.id.clean);
        clean.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String all = "ALL";
                dbHelper.delete("ALL");
                dbHelper2.delete(0);
                items.clear();
                adapter.notifyDataSetChanged();
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
}

