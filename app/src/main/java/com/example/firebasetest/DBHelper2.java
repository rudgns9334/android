package com.example.firebasetest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper2 extends SQLiteOpenHelper {

    public DBHelper2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE Recommend (R_id INTEGER PRIMARY KEY, mcount INTEGER, scount INTEGER, flag INTEGER);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(int R_id, int mcount) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        if(!isEqual(R_id))
            db.execSQL("INSERT INTO Recommend VALUES(" + R_id + ", " + mcount + ", 0, 0);");
        db.close();
    }


    public void delete(int R_id) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        if(R_id == 0){
            db.execSQL("DELETE FROM Recommend;");
        }
        db.execSQL("DELETE FROM Recommend WHERE R_id = " + R_id + ";");
        db.close();
    }

    public void update_flag(int R_id, int tf){
        SQLiteDatabase db = getReadableDatabase();
        if(tf == 1)
            db.execSQL("UPDATE Recommend SET flag=1 WHERE R_id=" + R_id+";");
        else
            db.execSQL("UPDATE Recommend SET flag=0 WHERE R_id=" + R_id+";");
        db.close();
    }

    public void cntup(int R_id){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("UPDATE Recommend SET scount=scount+1 WHERE R_id=" + R_id+";");
        Cursor cursor = db.rawQuery("SELECT * FROM Recommend", null);
        int mcount,scount=0;
        while (cursor.moveToNext()) {
            if(cursor.getInt(0)==R_id){
                mcount = cursor.getInt(1);
                scount = cursor.getInt(2);
                if((int)((float)scount/mcount*100) >= 80)
                    update_flag(R_id, 1);
            }
        }
        db.close();
    }

    public void cntdown(int R_id){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("UPDATE Recommend SET scount=scount-1 WHERE R_id=" + R_id+";");
        Cursor cursor = db.rawQuery("SELECT * FROM Recommend", null);
        int mcount,scount=0;
        while (cursor.moveToNext()) {
            if(cursor.getInt(0)==R_id){
                mcount = cursor.getInt(1);
                scount = cursor.getInt(2);
                if((int)((float)scount/mcount*100) < 80)
                    update_flag(R_id, 0);
                if(cursor.getInt(2) == 0)
                    delete(R_id);
            }
        }
        db.close();
    }

    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "\n";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM Recommend", null);
        while (cursor.moveToNext()) {
            result += cursor.getInt(0) + " "+ cursor.getInt(1) +" "+ cursor.getInt(2) + " "+ cursor.getInt(3) + "\n";
        }
        return result;
    }
    public String getRecipy() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "\n";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM Recommend", null);
        while (cursor.moveToNext()) {
            if(cursor.getInt(3) == 1)
               result += cursor.getInt(0) + " "+ cursor.getInt(1) +" "+ cursor.getInt(2) + " "+ cursor.getInt(3) + "\n";
        }
        return result;
    }

    public boolean isEqual(int R_id){
        boolean isExist = false;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Recommend", null);
        while (cursor.moveToNext()) {
            if(cursor.getInt(0) == R_id) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }
}
