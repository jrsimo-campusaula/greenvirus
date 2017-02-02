package aulacampus.valenciaenverd;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    //Variables para almacenar el fragmento que contendrá el mapa del tipo MapFragment, y el mapa de la API de Google
    private MapFragment fragmentoMapa;
    private GoogleMap mapa;
    private GoogleApiClient apiGoogle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Iniciamos el mapa de Google
        iniciarMapa();
        
        crearApiGoogle();

    }

    private void crearApiGoogle() {
        Log.d(TAG, "createGoogleApi()");
        if ( apiGoogle == null ) {
            apiGoogle = new GoogleApiClient.Builder( this )
                    .addConnectionCallbacks( this )
                    .addOnConnectionFailedListener( this )
                    .addApi( LocationServices.API )
                    .build();
        }
    }

    // Initialize GoogleMaps
    private void iniciarMapa() {
        fragmentoMapa = (MapFragment) getFragmentManager().findFragmentById(R.id.mapa);
        fragmentoMapa.getMapAsync(this);
    }

    // Callback called when Map is ready
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapa = googleMap;
        mapa.setOnMapClickListener(this);
        mapa.setOnMarkerClickListener(this);
    }

    // Callback called when Map is touched
    @Override
    public void onMapClick(LatLng latLng) {
    }

    // Callback called when Marker is touched
    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }



    @Override
    protected void onStart() {
        super.onStart();

        // Llama a la API de Google cada vez que se inicia la actividad
        apiGoogle.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Se desconecta de Google cada vez que la actividad se va a finalizar
        apiGoogle.disconnect();
    }

    // Callback de la API para cuando se conecta
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "onConnected()");
    }

    // Callback de la API para cuando se suspende
    @Override
    public void onConnectionSuspended(int i) {
        Log.w(TAG, "onConnectionSuspended()");
    }

    // Si no se consigue realizar la conexión
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "onConnectionFailed()");
    }
}
