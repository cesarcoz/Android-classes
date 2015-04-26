package com.cesarcoz.clase.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Administrator on 25/04/2015.
 */
public class ServiceExample extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(this.getClass().getName(),"onStartCommand ServiceExample");

        new Thread(new Runnable() {
            @Override
            public void run() {
                extendedTask();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    private void extendedTask() {
        for (int i=0; i<10;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(this.getClass().getName(),"onCreate ServiceExample");
    }
}
