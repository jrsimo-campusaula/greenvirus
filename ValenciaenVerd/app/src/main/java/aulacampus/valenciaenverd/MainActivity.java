package aulacampus.valenciaenverd;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.carto.core.MapPos;
import com.carto.layers.CartoBaseMapStyle;
import com.carto.layers.CartoOnlineVectorTileLayer;
import com.carto.ui.MapView;
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

public class MainActivity extends AppCompatActivity{


        private MapView mapa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Registro de la licencia para Carto, si, la API KEY es un troncho, se han pasao
        MapView.registerLicense("XTUMwQ0ZFYVBubjNsa2Z3c2x3bEQ5SjhTMDdWN2xUWjdBaFVBbW1hekZYdTFsQzlWR1BhWk9MMmNJcVo3OHBVPQoKYXBwVG9rZW49ZmE0MjFkN2EtYTQ5YS00ZGIzLWJiYzYtYTA0MzE4Nzc0YjNmCnBhY2thZ2VOYW1lPWF1bGFjYW1wdXMudmFsZW5jaWFlbnZlcmQKb25saW5lTGljZW5zZT0xCnByb2R1Y3RzPXNkay1hbmRyb2lkLTQuKgp3YXRlcm1hcms9Y2FydG9kYgo=", getApplicationContext());

        //Instanciamos el Widget que contendrá el mapa
        mapa = (MapView) findViewById(R.id.mapa);

        //Obtenemos la capa Base del mapa
        CartoOnlineVectorTileLayer capaBase = new CartoOnlineVectorTileLayer(CartoBaseMapStyle.CARTO_BASEMAP_STYLE_DARK);

        //Añadimos la capa base al mapa
        mapa.getLayers().add(capaBase);

        // Obtenemos una posición en el mapa, y la colocamos para que aparezca por defecto
        MapPos valencia = mapa.getOptions().getBaseProjection().fromWgs84(new MapPos(-0.3762881, 39.4699075));
        mapa.setFocusPos(valencia, 0);
        mapa.setZoom(12, 0);



        setTitle("Mapa Polen");

    }

}
