package com.barlocalizer.services;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.barlocalizer.R;
import com.barlocalizer.screens.CountDownActivity;

/**
 * Service de notification.
 * 
 * @author martinelli-b
 */
public class StatusNotificationService extends Service {

    /** Manager de notifications android. */
    private NotificationManager mNotificationManager = null;

    /** Identifiant de la notification produite. */
    private static final int ID_NOTIFICATION = 1;

    /** . */
    private static final int MINUTES_AVANT_LANCEMENT_NOTIFICATION = 30;

    /** . */
    private static final int TEMPORISATION = FabriqueDureeEnMillisecondes.transformerSecondesEnMillisecondes(1);

    /** . */
    private Timer timer = null;

    /** . */
    private boolean notifAlreadyShown = false;

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (FabriqueDate.getDateDepartPourCallaghan().getTimeInMillis()
                        - Calendar.getInstance().getTimeInMillis() <= FabriqueDureeEnMillisecondes
                        .transformerMinutesEnMillisecondes(MINUTES_AVANT_LANCEMENT_NOTIFICATION) && !notifAlreadyShown) {
                    notifAlreadyShown = true;
                    showNotification();
                }
            }
        }, 0, TEMPORISATION);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mNotificationManager != null) {
            mNotificationManager.cancel(ID_NOTIFICATION);
        }
    }

    /**
     * Envoie une notification.
     */
    private void showNotification() {
        CharSequence text = getText(R.string.notificationtiny);

        Notification notification = new Notification(R.drawable.mapmarker, text, System.currentTimeMillis());
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, CountDownActivity.class), 0);
        notification.setLatestEventInfo(this, getText(R.string.notificationtiny),
                getText(R.string.notificationexpended), contentIntent);

        mNotificationManager.notify(ID_NOTIFICATION, notification);
    }

    @Override
    public IBinder onBind(final Intent arg0) {
        return null;
    }

}
