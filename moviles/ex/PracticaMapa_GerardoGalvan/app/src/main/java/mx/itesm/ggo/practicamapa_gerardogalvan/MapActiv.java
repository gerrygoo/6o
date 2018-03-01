package mx.itesm.ggo.practicamapa_gerardogalvan;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActiv extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private final int PERMISO_GPS = 200;
    private LocationManager gps;
    private int locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locations = 0;
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
            gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
        }
    }




    // OnMapReadyCallback
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    @SuppressLint("MissingPermission")
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng tec = new LatLng(19.594210, -99.228167);
        mMap.addMarker(new MarkerOptions().position(tec).title("Tec").snippet("ITESM Campus Estado de México"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tec, 18));
    }




    // LocationListener
    @Override
    public void onLocationChanged(Location location) {
        LatLng loc = new LatLng( location.getLatitude(), location.getLongitude() );
        mMap.addMarker( new MarkerOptions().position(loc).title("Location no." + locations++ ) );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 18));
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
