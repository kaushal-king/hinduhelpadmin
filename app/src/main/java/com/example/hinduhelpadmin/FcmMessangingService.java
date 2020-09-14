package com.example.hinduhelpadmin;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class FcmMessangingService extends FirebaseMessagingService {

    String title;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

//        String title = remoteMessage.getNotification().getTitle();
//        String message =remoteMessage.getNotification().getBody();
//        int requestID = (int) System.currentTimeMillis();
//      Intent intent = new Intent(this, MainActivity.class);
//        /*intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//       // PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID,intent, PendingIntent.FLAG_UPDATE_CURRENT);
//      Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setContentTitle(title)
//                .setSmallIcon(R.drawable.logo)
//                .setContentText(message)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(requestID, notificationBuilder.build());

        // Create an explicit intent for an Activity in your app
      /*  Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "chanrl_id")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(new Random(9999).nextInt(), builder.build());*/

        if (remoteMessage.getData() != null) {

            sendNotification(remoteMessage);
        }
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

         String title = data.get("title");
       String content = data.get("content");


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "admin";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant")
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "admin Notification",
                    NotificationManager.IMPORTANCE_MAX);


            notificationChannel.setDescription("admin Data for test");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }
        Random rand = new Random(); //instance of random class
        int upperbound = 99999;
        //generate random values from 0-24
        int int_random = rand.nextInt(upperbound);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("t",title);
       // intent.putExtra("te",int_random);
        intent.putExtra("d","true");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = null;


            pendingIntent = PendingIntent.getActivity(this, int_random, intent, 0);


        NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);

        notificationbuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.logo_round)
                .setTicker("admin")
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(content))
                .setContentText(content)
                .setContentInfo("ainfo");
        notificationManager.notify(int_random,notificationbuilder.build());



    }
}
