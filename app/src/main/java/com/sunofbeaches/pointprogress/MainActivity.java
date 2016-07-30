package com.sunofbeaches.pointprogress;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.ImageView;

import com.sunofbeaches.pointprogress.view.SCN_PointProgress;

public class MainActivity extends Activity {

    private ImageView mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                //找到控件
                mProgress = (ImageView) stub.findViewById(R.id.sync_data_progress);

                //创建出来，设置进去，就可以啦，！哈哈，此处应有掌声！

                mProgress.setBackground(new SCN_PointProgress(MainActivity.this, 3));
            }
        });
    }
}
