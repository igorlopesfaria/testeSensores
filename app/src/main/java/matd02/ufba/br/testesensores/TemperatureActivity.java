package matd02.ufba.br.testesensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class TemperatureActivity extends AppCompatActivity implements SensorEventListener {

    private TextView sensorTX, x, y, z;
    private SensorManager sensorManager;
    private Sensor temperatureSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        init();
        sensorTX.setText("Temperatura");
        Log.v("TESTE SENSORES", "Giroscópio");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (temperatureSensor == null) {
            y.setText("Esse aparelho  não possui Sensor de Temperatura de Ambiente");

        }

        sensorManager.registerListener(this,
                temperatureSensor,
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
        if (sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float[] values = sensorEvent.values;

            x.setText(values[0]+ "");

            Log.v("TESTE SENSORES", "TEMPERATURA= "+values[0] +"C" );


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
