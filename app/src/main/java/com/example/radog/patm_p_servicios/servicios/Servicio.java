package com.example.radog.patm_p_servicios.servicios;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by radog on 02/04/2017.
 */

public class Servicio extends Service implements LocationListener {

    private final Context con;

    double latitud;
    double longitud;
    Location location;
    boolean gpsActivo = false;
    TextView texto;
    LocationManager locationManager;

    public Servicio() {
        super();
        con = this.getApplicationContext();

    }

    public Servicio(Context con) {
        super(); //para que se ejecute el constructor de Service original
        this.con = con;
        getLocation();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void setView(TextView tv) {
        //texto = (TextView) v;
        tv.setText("Coordenadas: " + latitud + " , " + longitud);
    }

    //conecta directamente con maganer, locationlistener
    //obtener latitud y longitud
    public void getLocation() {
        try {
            //el contexto permite acceder a los servicios del sistema
            //se usará el servicio de ubicación
            locationManager = (LocationManager) this.con.getSystemService(LOCATION_SERVICE);
            //puede hacer también con NETWORK_PROVIDER
            gpsActivo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (gpsActivo) {
                //petición al servicio del sistema
                //solicita actualizaciones de la ubicación gps cada cierto tiempo
                //1000*60 = cada minuto
                //cada 10 metros se actualizará el gps
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 1000 * 60, 10, this);
            }

            //obtiene la última ubicación conocida
            location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            latitud = location.getLatitude();
            longitud = location.getLongitude();

            //Toast.makeText(con, "Coordenadas: " + latitud + " , " + longitud, Toast.LENGTH_LONG).show();

        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(con, e.toString(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(con, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
