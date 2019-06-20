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
import android.widget.TextView;

import com.example.advance.R;

/**
 * @author jinxin
 */
public class MyNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MyNotificationActivity";

    private TextView tvNormal;
    private NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notification);

        tvNormal = (TextView) findViewById(R.id.tv_nomal);

        tvNormal.setOnClickListener(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nomal:
                sendNormalNotification();
                break;
                default:
        }
    }

    private void sendNormalNotification() {
        String id = "channel_001";
        String name = "name";
        Notification notification = null;
        //判断API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(this,id)
                    .setChannelId(id)
                    .setContentTitle("活动")
                    .setContentText("您有一项新活动")
                    .setSmallIcon(R.mipmap.ic_launcher_round).build();
        } else {
            Notification.Builder builder  = new Notification.Builder(this);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            //添加Notification属性
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground));
            builder.setAutoCancel(true);
            builder.setContentTitle("普通通知");
            builder.setContentText("public");
            // 任何情况下显示
            builder.setVisibility(Notification.VISIBILITY_PUBLIC);
            notification = builder.build();
        }

        notificationManager.notify(0, notification);
        Log.d(TAG, "sendNormalNotification: ");
    }
//
//    private void selectNotofovatiomLevel(Notification.Builder builder) {
//        switch (radioGroup.getCheckedRadioButtonId()) {
//            case R.id.rb_public:
//                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
//                builder.setContentText("public");
//                break;
//            case R.id.rb_private:
//                builder.setVisibility(Notification.VISIBILITY_PRIVATE);
//                builder.setContentText("private");
//                break;
//            case R.id.rb_secret:
//                builder.setVisibility(Notification.VISIBILITY_SECRET);
//                builder.setContentText("secret");
//                break;
//            default:
//                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
//                builder.setContentText("public");
//                break;
//
//        }
//    }
}
