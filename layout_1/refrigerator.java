package com.example.layout_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class refrigerator extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refrigerator);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("나의 냉장고");

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Refrigerator.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = (TextView) findViewById(R.id.result);

        final EditText etItem = (EditText) findViewById(R.id.item);

        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                if(item.equals(""))
                    Toast.makeText(refrigerator.this, "재료를 입력하세요 !", Toast.LENGTH_SHORT).show();
                else if(dbHelper.isEqual(item) == true)
                    Toast.makeText(refrigerator.this, "이미 냉장고 안에 있어요 !", Toast.LENGTH_SHORT).show();
                else
                    dbHelper.insert(item);
                result.setText(dbHelper.getResult());
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
                    Toast.makeText(refrigerator.this, "방금 입력한 재료는 없어요 !", Toast.LENGTH_SHORT).show();
                else
                    dbHelper.delete(item);

                result.setText(dbHelper.getResult());
                etItem.setText(null);
            }
        });

        // DB에 있는 데이터 조회
        Button select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(dbHelper.getResult());
            }
        });

        Button clean = (Button) findViewById(R.id.clean);
        clean.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String all = "ALL";
                dbHelper.delete("ALL");
                result.setText(dbHelper.getResult());
            }
        });

    }
}
