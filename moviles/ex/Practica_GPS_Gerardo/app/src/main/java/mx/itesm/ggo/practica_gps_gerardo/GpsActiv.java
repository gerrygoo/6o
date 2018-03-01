package mx.itesm.ggo.practica_gps_gerardo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.BitmapRequestListener;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GpsActiv extends AppCompatActivity implements LocationListener {

    private final int PERMISO_GPS = 200;
    private final String API_KEY = "AIzaSyAICAmQG9ltu3go0Au1TxHuWobMwTeEClk";
    private final String API_KEY_IMAGE = "AIzaSyBpG2DnMLege8Kzorhw6UmXg2b0mLg-t-8";

    private LocationManager gps;
    private Location posicion;

    private TextInputEditText etLatitud;
    private TextInputEditText etLongitud;
    private TextInputEditText etDireccion;
    private ImageView mapimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        AndroidNetworking.initialize(getApplicationContext());


        etLatitud = findViewById(R.id.etLatitud);
        etLongitud= findViewById(R.id.etLongitud);
        etDireccion = findViewById(R.id.etDireccion);
        mapimg = findViewById(R.id.mapimg);

        configurarGPS();
    }

    private void configurarGPS(){
        gps = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(!gps.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            prenderGPS();
        }

    }

    private void prenderGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El GPS esta apagado ¿quieres prenderlo?")
                .setCancelable(false)
                .setPositiveButton(
                        "Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) { startActivity(new
                                    Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)); // Abre settings
                            }
                        }
                )
                .setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                            }
                        }
                );
        final AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    @SuppressLint("MissingPermission")
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISO_GPS && grantResults.length>0){
            if ( grantResults[0] == PackageManager.PERMISSION_GRANTED )
                gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
            else
                Log.i("onRequestPerm...", "NOT GRANTED");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISO_GPS
            );
        }else{
            gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gps, menu);
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



    // LocationListener methods
    @Override
    public void onLocationChanged(Location location) {
        etLatitud.setText(location.getLatitude() + "");
        etLongitud.setText(location.getLongitude() + "");

        posicion = location;
        String address;

        AndroidNetworking.get("https://maps.googleapis.com/maps/api/geocode/json")

                .addQueryParameter("latlng", location.getLatitude() +  "," + location.getLongitude() )
                .addQueryParameter("key", API_KEY )

                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            etDireccion.setText( response.getJSONArray("results").getJSONObject(0).getString("formatted_address") );


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

        AndroidNetworking.get("https://maps.googleapis.com/maps/api/staticmap")

                .addQueryParameter("center", location.getLatitude() +  "," + location.getLongitude() )
                .addQueryParameter("size", "640x480" )
                .addQueryParameter("zoom", "18" )
                .addQueryParameter("maptype", "satellite" )
                .addQueryParameter("markers", location.getLatitude() +  "," + location.getLongitude() )

                .addQueryParameter("key", API_KEY_IMAGE )

                .build().getAsBitmap(new BitmapRequestListener() {
                    @Override
                    public void onResponse(Bitmap response) {
                        mapimg.setImageBitmap(response);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
