package com.example.a46453895j.cartas;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private View view;
    private ImageView Ivdetalle;
    private TextView Tvinfodetalle;
    private TextView Tvtitulodetalle;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_detail, container, false);
        Intent i=getActivity().getIntent();

        if (i!=null) {
            Ocarta carta = (Ocarta) i.getSerializableExtra("carta");

            if (carta != null) {
                updateUi(carta);
            }
        }
        return view;
    }
    private void updateUi(Ocarta carta){

        Ivdetalle=(ImageView)view.findViewById(R.id.Ivdetalle);
        Tvinfodetalle=(TextView)view.findViewById(R.id.Tvinfodetalle);
        Tvtitulodetalle=(TextView)view.findViewById(R.id.Tvtitulodetalle);

        Tvtitulodetalle.setText(carta.getTitulo());
        Tvinfodetalle.setText(carta.getDescripcion());
        Glide.with(getContext()).load(carta.getImageUrl()).into(Ivdetalle);

    }
}
