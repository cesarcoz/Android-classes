package clases.cesarcoz.com.alumnadofragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 18/04/2015.
 */
public class AulasFragment extends ListFragment {

    OnAulaSeleccionada onAulaSeleccionadaCallback;

    public interface OnAulaSeleccionada{
        public void colocarAulaSeleccionada(int posicion);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,Data.Aulas));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onAulaSeleccionadaCallback = (OnAulaSeleccionada) activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        onAulaSeleccionadaCallback.colocarAulaSeleccionada(position);
    }
}
