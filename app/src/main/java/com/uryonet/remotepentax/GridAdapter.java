package com.uryonet.remotepentax;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private int layoutId;
    private List<String> imageList = new ArrayList<>();

    private int ScreenWidthHalf = 0;

    GridAdapter(Context context, int layoutId, String[] iList) {
        super();
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layoutId;

        Collections.addAll(imageList, iList);

        // 画面の横幅の半分を計算
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display disp = wm.getDefaultDisplay();
            Point size = new Point();
            disp.getSize(size);

            int screenWidth = size.x;
            ScreenWidthHalf = screenWidth / 2;
            Log.d("debug", "ScreenWidthHalf=" + ScreenWidthHalf);
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = inflater.inflate(layoutId, parent, false);
        } else {
            view = convertView;
        }

        ImageView img = view.findViewById(R.id.imageView);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Picasso.with(context).load(addUrl(position)).resize(ScreenWidthHalf, ScreenWidthHalf).into(img);

        return view;
    }

    // ネットワークアクセスするURLを設定する
    private String addUrl(int number) {
        return String.format(Locale.US, "http://192.168.0.1/v1/photos/%s?size=thumb", imageList.get(number));
    }

    public int getCount() {
        return imageList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
}
