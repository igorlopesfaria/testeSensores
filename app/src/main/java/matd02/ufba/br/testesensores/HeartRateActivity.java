package matd02.ufba.br.testesensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class HeartRateActivity extends AppCompatActivity implements SensorEventListener {

    private TextView sensorTX, x, y, z;
    private SensorManager sensorManager;
    private Sensor heartRateSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        init();
        sensorTX.setText("Batimento Cardíaco");
        Log.v("TESTE SENSORES", "Batimento Cardíaco");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }
    @Override
    protected void onResume() {
        super.onResume();
        heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        if (heartRateSensor == null) {
            y.setText("Esse aparelho  não possui Sensor de Batimento Cardíaco");

        }

        sensorManager.registerListener(this,
                heartRateSensor,
                SensorManager.SENSOR_DELAY_NORMAL);


        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor s : deviceSensors){
            Log.i("TESTE SENSORES", "" + s.getName());
        }

    }
    private void init() {
        sensorTX = (TextView) findViewById(R.id.sensorTX);
        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);

    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            float[] values = sensorEvent.values;

            x.setText(values[0]+"");
            y.setText("");

            Log.v("TESTE SENSORES", "BATIMENTO CARDÍACO= "+values[0] );

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
