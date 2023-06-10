package com.dreafaa.aohoifsd;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BannerImageVIew extends LinearLayout {

    Banner banner1;
    Banner banner2;
    Banner banner3;

    public BannerImageVIew(Context context) {
        super(context);
        init(context);
    }

    public BannerImageVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BannerImageVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        banner1 = new Banner(context);
        banner2 = new Banner(context);
        banner3 = new Banner(context);
        setGravity(0x11);
        setOrientation(HORIZONTAL);
        List<Integer> Imgs = new ArrayList<>();
        
        ViewGroup.LayoutParams leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 400, 1);
        initBanner(context, Imgs, leftParams, banner1);
        initBanner(context, Imgs, leftParams, banner2);
        initBanner(context, Imgs, leftParams, banner3);

        addView(banner1);
        addView(banner2);
        addView(banner3);
    }

    public void start(Context context) {
        Random random = new Random();
        banner1.isAutoLoop(true);
        banner1.setLoopTime(500);
        banner1.setCurrentItem(random.nextInt(5));
        banner1.start();

        banner2.isAutoLoop(true);
        banner2.setLoopTime(300);
        banner2.setCurrentItem(random.nextInt(5));
        banner2.start();

        banner3.isAutoLoop(true);
        banner3.setLoopTime(100);
        banner3.setCurrentItem(random.nextInt(5));
        banner3.start();

        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(2000);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Parabéns, você ganhou um prêmio!", Toast.LENGTH_SHORT).show();
                            banner1.stop();
                            banner2.stop();
                            banner3.stop();
                        }
                    });
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    private void initBanner(Context context, List<Integer> Imgs, ViewGroup.LayoutParams leftParams, Banner banner) {
        banner.setLayoutParams(leftParams);
        banner.setAdapter(new BannerImageAdapter(Imgs) {
            @Override
            public void onBindView(Object holder, Object data, int position, int size) {
                ImageView imageView = ((BannerImageHolder) holder).imageView;
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                Glide.with(context).load(Imgs.get(position)).into(imageView);
            }
        });
        banner.setOrientation(Banner.VERTICAL);
        banner.setUserInputEnabled(false);
        banner.isAutoLoop(false);
    }
}
