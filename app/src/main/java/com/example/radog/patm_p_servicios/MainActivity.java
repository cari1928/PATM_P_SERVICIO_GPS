package com.example.radog.patm_p_servicios;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.radog.patm_p_servicios.servicios.Servicio;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textoUbicacion) TextView textoUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //creación del objeto
        Servicio objS = new Servicio(this);
        //objS.setView(rootView.findViewById(R.id.textoUbicacion));
        objS.setView(textoUbicacion);
    }
}
