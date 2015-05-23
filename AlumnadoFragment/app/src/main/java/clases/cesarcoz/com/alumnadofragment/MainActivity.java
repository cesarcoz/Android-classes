package clases.cesarcoz.com.alumnadofragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements AulasFragment.OnAulaSeleccionada{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_activitys);

        if (findViewById(R.id.fragment_container) != null) {
            AulasFragment aulasFragment = new AulasFragment();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, aulasFragment).commit();
        }
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
    public void colocarAulaSeleccionada(int posicion) {
        AlumnosFragment alumnosFragment = (AlumnosFragment) getFragmentManager().findFragmentById(R.id.alumno_fragment);
        if (alumnosFragment!=null){
            alumnosFragment.actualizarListaAlumnos(posicion);
        }else{
            alumnosFragment = new AlumnosFragment();
            Bundle args = new Bundle();
            args.putInt(alumnosFragment.AULA_SELECCIONADA,posicion);
            alumnosFragment.setArguments(args);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,alumnosFragment);
            fragmentTransaction.addToBackStack(AulasFragment.class.getName());
            fragmentTransaction.commit();
        }
    }
}
