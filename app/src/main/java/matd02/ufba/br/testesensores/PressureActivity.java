package matd02.ufba.br.testesensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class PressureActivity extends AppCompatActivity implements SensorEventListener {

    private TextView sensorTX, x, y, z;
    private SensorManager sensorManager;
    private Sensor myPressureSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        init();
        sensorTX.setText("Proximidade");

        Log.v("TESTE SENSORES", "Proximidade");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);



    }
    @Override
    protected void onResume() {
        super.onResume();

        // register this class as a listener for the orientation and
        // accelerometer sensors
        myPressureSensor =sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (myPressureSensor == null) {
            y.setText("Esse aparelho  não possui Sensor de Pressão do Ambiente");
        }
        sensorManager.registerListener(this,
                myPressureSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    private void init() {
        sensorTX = (TextView) findViewById(R.id.sensorTX);
        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float[] values = sensorEvent.values;
            x.setText(values[0]+"");

            Log.v("TESTE SENSORES", "Pressão= "+values[0]+ "%");

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
