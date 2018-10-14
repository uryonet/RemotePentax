package com.uryonet.remotepentax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.uryonet.remotepentax.service.model.PhotoList;

public class MainActivity extends AppCompatActivity {

    private static final String[] photoUrls = {
            "100_1010/IMGP5442.JPG",
            "100_1010/IMGP5443.JPG",
            "101_1011/IMGP5444.JPG",
            "101_1011/IMGP5445.JPG",
            "101_1011/IMGP5446.JPG",
            "101_1011/IMGP5447.JPG",
            "101_1011/IMGP5448.JPG",
            "101_1011/IMGP5449.JPG"
    };

    private PhotoList photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


//        // GridViewのインスタンス生成
//        GridView gridView = findViewById(R.id.gridView);
//
//        // GridAdapterのインスタンス生成
//        GridAdapter adapter = new GridAdapter(this.getApplicationContext(), R.layout.grid_items, photoUrls);
//
//        // GridViewにGridAdapterをセット
//        gridView.setAdapter(adapter);
    }
}
