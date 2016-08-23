package matd02.ufba.br.testesensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private TextView sensorTX, x, y, z;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        init();
        sensorTX.setText("Acelerômetro");
        Log.v("TESTE SENSORES", "Acelerômetro");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }
    @Override
    protected void onResume() {
        super.onResume();

        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    private void init() {
        sensorTX = (TextView) findViewById(R.id.sensorTX);
        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);
        z = (TextView) findViewById(R.id.z);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = sensorEvent.values;

            x.setText(values[0]+"");
            y.setText(values[1]+"");
            z.setText(values[2]+"");

            Log.v("TESTE SENSORES", "X= "+values[0] +" Y= "+values[1] +" Z= "+values[2]);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        // unregister listener
        sensorManager.unregisterListener(this);
    }
}
