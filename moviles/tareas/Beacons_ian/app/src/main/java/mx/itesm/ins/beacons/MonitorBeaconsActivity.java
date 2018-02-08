package mx.itesm.ins.beacons;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;
import java.util.UUID;

public class MonitorBeaconsActivity extends AppCompatActivity {

    private BeaconManager beaconManager;
    private BeaconRegion beaconRegion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_beacons);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        configurarBeacons();
    }

    private void configurarBeacons() {
        beaconManager = new BeaconManager(this);
        beaconRegion = new BeaconRegion("beaconsAM", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),30296, 46626);

        // Agregar el listener
        /*beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> beacons) {
                for(Beacon beacon : beacons){
                    Log.i("BeaconsIan", beacon.toString());
                }
                Log.i("BeaconsIanNumbers",beacons.size() + "");
            }
        });
        */
        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> beacons) {
                findViewById(R.id.VistaPrincipal).setBackgroundColor(Color.GREEN);
            }

            @Override
            public void onExitedRegion(BeaconRegion beaconRegion) {
                findViewById(R.id.VistaPrincipal).setBackgroundColor(Color.RED);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(beaconRegion);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        beaconManager.stopMonitoring(beaconRegion.getIdentifier());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_monitor_beacons, menu);
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
        if(id==R.id.action_exit){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
