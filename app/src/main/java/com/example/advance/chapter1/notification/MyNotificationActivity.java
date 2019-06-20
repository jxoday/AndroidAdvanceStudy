package com.example.advance.chapter1.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.advance.R;

/**
 * @author jinxin
 */
public class MyNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MyNotificationActivity";

    private TextView tvNormal;
    private TextView tvFold;
    private TextView tvHang;
    private NotificationManager notificationManager;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notification);

        tvNormal = (TextView) findViewById(R.id.tv_normal);
        tvFold = (TextView) findViewById(R.id.tv_fold);
        tvHang = (TextView) findViewById(R.id.tv_hang);

        radioButton1 = (RadioButton) findViewById(R.id.rb_public);
        radioButton2 = (RadioButton) findViewById(R.id.rb_private);
        radioButton2 = (RadioButton) findViewById(R.id.rb_secret);
        radioGroup = (RadioGroup) findViewById(R.id.rg_all);

        tvNormal.setOnClickListener(this);
        tvFold.setOnClickListener(this);
        tvHang.setOnClickListener(this);
        // notification管理
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_normal:
                // 普通Notification
                sendNormalNotification();
                break;
            case R.id.tv_fold:
                // 展开Notification
                sendFoldNotification();
                break;
            case R.id.tv_hang:
                // 悬挂Notification
                sendHangNotification();
                break;
            default:
        }
    }

    /**
     * Notification
     */
    private void sendNormalNotification() {
        String id = "channel_001";
        String name = "name";
        Notification notification = null;
        // 判断API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(this, id)
                    .setChannelId(id)
                    .setContentTitle("活动")
                    .setContentText("您有一项新活动")
                    .setSmallIcon(R.mipmap.ic_launcher_round).build();
        } else {
            Notification.Builder builder = new Notification.Builder(this);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            // 添加Notification属性
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.mipmap.ic_launcher_round);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
            builder.setAutoCancel(true);
            builder.setContentTitle("普通通知");
            // 任何情况下显示
//            builder.setVisibility(Notification.VISIBILITY_PUBLIC);
//            builder.setContentText("public");
            selectNotificationLevel(builder);
            notification = builder.build();
        }

        notificationManager.notify(0, notification);
        Log.d(TAG, "sendNormalNotification: ");
    }

    private void sendFoldNotification() {
        String id = "channel_002";
        String name = "name";
        Notification notification = null;
        // 判断API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            // 用RemoteViews来创建自定义notification视图
            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_fold);
            notification = new Notification.Builder(this, id)
                    .setChannelId(id)
                    .setContentTitle("活动")
                    .setContentText("您有一项新活动")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setCustomBigContentView(remoteViews)
                    .build();
        } else {
            Notification.Builder builder = new Notification.Builder(this);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            // 添加Notification属性
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.drawable.foldleft);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.foldleft));
            builder.setAutoCancel(true);
            builder.setContentTitle("折叠式通知");
            // 任何情况下显示
//            builder.setVisibility(Notification.VISIBILITY_PUBLIC);
//            builder.setContentText("public");
            selectNotificationLevel(builder);
            // 用RemoteViews来创建自定义notification视图
            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_fold);
            notification = builder.build();
            notification.bigContentView = remoteViews;
        }

        notificationManager.notify(1, notification);
        Log.d(TAG, "sendFoldNotification: ");
    }

    private void sendHangNotification() {
        String id = "channel_003";
        String name = "name";
        Notification notification = null;
        // 判断API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            // 设置悬挂以及点击跳转
            Intent hangIntent = new Intent();
            hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            hangIntent.setClass(this, MyNotificationActivity.class);
            //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
            PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            notification = new Notification.Builder(this, id)
                    .setChannelId(id)
                    .setContentTitle("活动")
                    .setContentText("您有一项新活动")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setFullScreenIntent(hangPendingIntent, true)
                    .build();
        } else {
            Notification.Builder builder = new Notification.Builder(this);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            // 添加Notification属性
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.drawable.foldleft);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.foldleft));
            builder.setAutoCancel(true);
            builder.setContentTitle("悬挂式通知");
            // 任何情况下显示
//            builder.setVisibility(Notification.VISIBILITY_PUBLIC);
//            builder.setContentText("public");
            selectNotificationLevel(builder);
            // 设置悬挂以及点击跳转
            Intent hangIntent = new Intent();
            hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            hangIntent.setClass(this, MyNotificationActivity.class);
            //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
            PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setFullScreenIntent(hangPendingIntent, true);
            notification = builder.build();
        }

        notificationManager.notify(2, notification);
        Log.d(TAG, "sendHangNotification: ");
    }

    /**
     * 设置 Notification等级 一共有三种等级
     * @param builder Notification
     */
    private void selectNotificationLevel(Notification.Builder builder) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_public:
                // 任何情况下都会通知
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                break;
            case R.id.rb_private:
                // 只有在没有锁屏时才会通知
                builder.setVisibility(Notification.VISIBILITY_PRIVATE);
                builder.setContentText("private");
                break;
            case R.id.rb_secret:
                // 在pin、password等安全锁和没有锁屏的情况下才能够显示通知
                builder.setVisibility(Notification.VISIBILITY_SECRET);
                builder.setContentText("secret");
                break;
            default:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                break;

        }
    }
}
