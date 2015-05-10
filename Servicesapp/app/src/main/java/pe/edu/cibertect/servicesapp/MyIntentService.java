package pe.edu.cibertect.servicesapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 25/04/2015.
 */
public class MyIntentService extends IntentService {

    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService(){
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(this.getClass().getName(), "onHandleIntent");
        tareaProlongada();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(this.getClass().getName(), "onStartCommand");

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
}
