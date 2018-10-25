package com.uryonet.remotepentax.view.adapter;

import android.databinding.BindingAdapter;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.uryonet.remotepentax.app.MyApplication;
import com.uryonet.remotepentax.service.repository.CameraService;

import static android.content.Context.WINDOW_SERVICE;

public class CustomBindingAdapter {

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("imageUrl")
    public static void imageUrl(ImageView imageView, String url) {

        int ScreenWidthHalf = 0;

        // 画面の横幅の半分を計算
        WindowManager wm = (WindowManager)MyApplication.getAppContext().getSystemService(WINDOW_SERVICE);
        if(wm != null){
            Display disp = wm.getDefaultDisplay();
            Point size = new Point();
            disp.getSize(size);

            int screenWidth = size.x;
            ScreenWidthHalf = screenWidth / 3 - 6;
        }

        Picasso.get().load(CameraService.HTTP_CAMERA_API_URL + "photos/" + url + "?size=view").resize(ScreenWidthHalf, ScreenWidthHalf).centerCrop().into(imageView);
    }
}
