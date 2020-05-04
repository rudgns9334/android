package com.example.lasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Refrigerator.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = (TextView) findViewById(R.id.result);

        final EditText etItem = (EditText) findViewById(R.id.item);
        final EditText etAmount = (EditText) findViewById(R.id.amount);

        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                String tempAmount = etAmount.getText().toString();
                if(tempAmount.equals("")) {
                    Toast.makeText(MainActivity.this, "양을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    int amount = Integer.parseInt(etAmount.getText().toString());
                    if (dbHelper.is_equal(item)[0] == 1)
                        dbHelper.update(item, amount + dbHelper.is_equal(item)[1]);
                    else
                        dbHelper.insert(item, amount);
                    result.setText(dbHelper.getResult());
                }
            }
        });

        // DB에 있는 데이터 수정
        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                int amount = Integer.parseInt(etAmount.getText().toString());

                dbHelper.update(item, amount);
                result.setText(dbHelper.getResult());
            }
        });

        // DB에 있는 데이터 삭제
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                String tempAmount = etAmount.getText().toString();

                if(tempAmount.equals("")) {
                    dbHelper.delete(item);
                }else {
                    int amount = Integer.parseInt(etAmount.getText().toString());
                    if (dbHelper.is_equal(item)[0] == 1 && dbHelper.is_equal(item)[1] > amount)
                        dbHelper.update(item, dbHelper.is_equal(item)[1] - amount);
                    else if(dbHelper.is_equal(item)[1] < amount)
                        Toast.makeText(MainActivity.this, "수량 초과입니다.", Toast.LENGTH_SHORT).show();

                }
                result.setText(dbHelper.getResult());
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
    }
}