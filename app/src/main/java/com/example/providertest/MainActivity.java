package com.example.providertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
    private Button addData, queryData, upDate, delData;
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addData = findViewById(R.id.add_data);
        upDate = findViewById(R.id.update_data);
        delData = findViewById(R.id.delete_data);
        queryData = findViewById(R.id.query_data);


        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("content://com.example.broadcastbestpractice.provider/book");
                ContentValues values=new ContentValues();
                values.put("name","A Clash of Kings");
                values.put("author","George Martin");
                values.put("pages",1040);
                values.put("price",22.85);
                Uri newUri=getContentResolver().insert(uri,values);
                newId=newUri.getPathSegments().get(1);
            }
        });

        upDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("content://com.example.broadcastbestpractice.provider/book/"+newId);
                ContentValues values=new ContentValues();
                values.put("name","A Storm of Swords");
                values.put("pages",1216);
                values.put("price",24.05);
                getContentResolver().update(uri,values,null,null);
            }
        });

        delData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("content://com.example.broadcastbestpractice.provider/book/"+newId);
                getContentResolver().delete(uri,null,null);

            }
        });

        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("content://com.example.broadcastbestpractice.provider/book");
                Cursor cursor=getContentResolver().query(uri,null,null,null,null);
                if (cursor!=null){
                    while (cursor.moveToNext()){
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.e("MainActivity", "book name is "+name );
                        Log.e("MainActivity", "book author is "+author );
                        Log.e("MainActivity", "book pages is "+pages );
                        Log.e("MainActivity", "book price is "+price );
                    }
                    cursor.close();
                }

            }
        });

    }

}
