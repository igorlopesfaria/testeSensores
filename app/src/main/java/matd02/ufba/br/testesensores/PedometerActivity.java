package matd02.ufba.br.testesensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PedometerActivity extends AppCompatActivity implements SensorEventListener {

    private TextView sensorTX, x, y, z;
    private SensorManager sensorManager;
    private Sensor mStepDetectSensor, mStepCountSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        init();
        sensorTX.setText("Pedômetro");
        Log.v("TESTE SENSORES", "Pedômetro");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }
    @Override
    protected void onResume() {
        super.onResume();

        mStepDetectSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        mStepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        sensorManager.registerListener(this,
                mStepDetectSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,
                mStepCountSensor,
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
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            float[] values = sensorEvent.values;

            y.setText("Count:"+ values[1]+"");

            Log.v("TESTE SENSORES", "X= "+values[0]) ;

        }else if(sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR){
            x.setText("Detected at " + currentTimeStr());
        }


    }

    private String currentTimeStr() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(c.getTime());
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
