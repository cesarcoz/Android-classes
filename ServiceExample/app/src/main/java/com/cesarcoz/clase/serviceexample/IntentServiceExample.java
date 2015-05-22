package com.cesarcoz.clase.serviceexample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 25/04/2015.
 */
public class IntentServiceExample extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceExample(String name) {
        super(name);
    }

    public IntentServiceExample(){
        super("IntentServiceExample");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(this.getClass().getName(),"onHandleIntent IntentServiceExample");
        extendedTask();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(this.getClass().getName(),"onStartCommand IntentServiceExample");
        return super.onStartCommand(intent, flags, startId);
    }

    private void extendedTask() {
        for (int i=0; i<10;i++){
            try {
                Log.d(this.getClass().getName(),"Extended task number"+i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
