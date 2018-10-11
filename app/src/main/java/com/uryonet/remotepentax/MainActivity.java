package com.uryonet.remotepentax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // GridViewのインスタンス生成
        GridView gridView = findViewById(R.id.gridView);

        // GridAdapterのインスタンス生成
        GridAdapter adapter = new GridAdapter(this.getApplicationContext(), R.layout.grid_items, photoUrls);

        // GridViewにGridAdapterをセット
        gridView.setAdapter(adapter);
    }
}
