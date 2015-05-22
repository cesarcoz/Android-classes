package pe.edu.cibertect.fragmentos;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements FragmentoUno.Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @Override
    public void sendMessage(String msg) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentoDos fragmentTwo = (FragmentoDos) fragmentManager.findFragmentById(R.id.fragmentodos);
        if (fragmentTwo!=null && fragmentTwo.isVisible()){
            fragmentTwo.changeMessage(msg);
        }else{
            callActivityTwo(msg);
        }
    }

    private void callActivityTwo(String msg) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("msg",msg);
        startActivity(intent);
    }
}
