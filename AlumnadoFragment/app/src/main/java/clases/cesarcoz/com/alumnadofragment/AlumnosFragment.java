package clases.cesarcoz.com.alumnadofragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 18/04/2015.
 */
public class AlumnosFragment extends Fragment {

    final static String AULA_SELECCIONADA = "aula_seleccionada";
    int actualAula = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alumnos_fragment,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args !=null){
            // Set article based on argument passed in
            actualizarListaAlumnos(args.getInt(AULA_SELECCIONADA));
        }else if (actualAula != -1){
            // Set articule based on saved instance state defined during onCreateView
            actualizarListaAlumnos(actualAula);
        }
    }

    public void actualizarListaAlumnos(int position) {
        Log.d("FRAGMENTO","actualizarListaAlumnos");
        Log.d(getActivity().getComponentName().toString(),getActivity().toString());

        TextView alumno = (TextView) getActivity().findViewById(R.id.txtalumnos);

        alumno.setText(Data.alumnos[position]);
        actualAula = position;
    }
}
