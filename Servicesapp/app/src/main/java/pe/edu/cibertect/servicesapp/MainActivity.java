package pe.edu.cibertect.servicesapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = (ImageView) findViewById(R.id.imgDescarga);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void procesarTx(View view) throws InterruptedException {
        // Thread.sleep(10*1000);
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }

    public void mostrarImg(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Bitmap b = cargarImagenDeRed("http://gulustan.info/wp-content/uploads/2015/02/musica-android.jpg");
                    //imagen.setImageBitmap(b);
                    imagen.post(new Runnable() {
                        @Override
                        public void run() {
                            imagen.setImageBitmap(b);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Bitmap cargarImagenDeRed(String url) throws IOException {
        InputStream inputStream = (InputStream) new URL(url).getContent();
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
}
