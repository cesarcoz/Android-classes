package pe.edu.cibertect.fragmentos;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 11/04/2015.
 */
public class FragmentoUno extends Fragment {

    public Communicator communicator;

    public interface Communicator {
        public void sendMessage(String msg);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentouno, container);

        final Button fragmentBtn = (Button) view.findViewById(R.id.btnFragment);
        fragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicator.sendMessage(fragmentBtn.getText().toString());
            }
        });

        final Button fragmentBtn2 = (Button) view.findViewById(R.id.btnFragment2);
        fragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicator.sendMessage(fragmentBtn2.getText().toString());
            }
        });
        return view;
    }
}
