package mx.itesm.ggo.beacons

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker
import com.estimote.coresdk.observation.region.beacon.BeaconRegion
import com.estimote.coresdk.recognition.packets.Beacon
import com.estimote.coresdk.service.BeaconManager

import kotlinx.android.synthetic.main.activity_beacon_main.*
import java.util.*

class BeaconMain : AppCompatActivity() {

    private lateinit var beaconManager: BeaconManager
    private lateinit var beaconRegion: BeaconRegion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beacon_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        beaconSetup()

    }


    private fun beaconSetup(){
        beaconManager = BeaconManager(this)
        beaconRegion = BeaconRegion(
                "MisBeacons",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                30296,
                46626)

        beaconManager.setMonitoringListener(
                object: BeaconManager.BeaconMonitoringListener{

                    override fun onEnteredRegion(beaconRegion: BeaconRegion?, beacons: MutableList<Beacon>?) {
                        Log.i("Beacons", beacons.toString())
                        mainview.setBackgroundColor(Color.GREEN)
                    }

                    override fun onExitedRegion(beaconRegion: BeaconRegion?) {
                        Log.i("Beacons", "")
                        mainview.setBackgroundColor(Color.RED)
                    }
                }
        )
//        beaconManager.setRangingListener( BeaconManager.BeaconRangingListener { _, beacons -> Log.i("Beacons", beacons.toString()) } )

    }


    override fun onResume() {
        super.onResume()
        SystemRequirementsChecker.checkWithDefaultDialogs(this)
        beaconManager.connect( { beaconManager.startMonitoring(beaconRegion) } )
    }

    override fun onPause() {
        beaconManager.stopRanging(beaconRegion)
        super.onPause()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_beacon_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
