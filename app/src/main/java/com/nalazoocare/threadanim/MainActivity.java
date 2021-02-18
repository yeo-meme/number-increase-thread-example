package com.nalazoocare.threadanim;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView mImageView;
    ArrayList<String> mList = new ArrayList<>();
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mList.add("1");
        mList.add("2");
        mList.add("3");
        mList.add("4");

        mImageView = findViewById(R.id.imageView);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimThread thread = new AnimThread();
                thread.start();
            }
        });
    }



    public class AnimThread extends Thread {
        @Override
        public void run() {
            int index = 0;
            while (true) {
              final String drawable = mList.get(index % mList.size());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setText(drawable);
                    }
                });
                index++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
