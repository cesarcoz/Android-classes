package pe.edu.cibertect.fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 11/04/2015.
 */
public class FragmentoDos extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentodos,container,false);
    }

    public void changeMessage(String msg) {
        TextView textView = (TextView) getView().findViewById(R.id.txtMsg);
        textView.setText(msg);
    }
}
