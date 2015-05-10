package pe.edu.cibertect.servicesapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;


public class CrearNotificacionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notificacion);
        generarNotificacion();


    }

    private void generarNotificacion() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent= new Intent(this,MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,0);
        Notification notification= new Notification.Builder(this)
                .setContentTitle("Notificacion Titulo")
                .setSmallIcon(R.drawable.ic_action_cloud)
                .setContentText("Texto de Notificacion")
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_action_cloud,"Call",pendingIntent)
                .build();


        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0,notification);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crear_notificacion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
