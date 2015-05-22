package pe.edu.cibertect.servicesapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Administrator on 25/04/2015.
 */
public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(this.getClass().getName(), "oncreate MyService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(this.getClass().getName(), "onStartCommand MyService");
        new Thread(new Runnable() {
            @Override
            public void run() {
                tareaProlongada();
            }
        }).start();



        return super.onStartCommand(intent, flags, startId);

    }

    private void tareaProlongada() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
