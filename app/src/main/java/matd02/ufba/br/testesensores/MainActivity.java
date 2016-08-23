package matd02.ufba.br.testesensores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import matd02.ufba.br.testesensores.adapter.SensorListAdapter;
import matd02.ufba.br.testesensores.listener.RecyclerItemClickListener;

public class MainActivity extends AppCompatActivity {

        private RecyclerView recycleListView;
        private SensorListAdapter mAdapter;
        private LinearLayoutManager mLayoutManager;
        private List<String> lString;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setAdapter(fillSensorList());

        recycleListView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {


                    @Override
                    public void onItemClick(View view, int position) {
                        Intent myIntent = null;

                        if(position ==0){
                            myIntent = new Intent(getApplicationContext(), AccelerometerActivity.class);
                        }else  if(position ==1){
                            myIntent = new Intent(getApplicationContext(), GyroscopeActivity.class);
                        }else  if(position ==2){
                            myIntent = new Intent(getApplicationContext(), MagnetometerActivity.class);
                        }else  if(position ==3){
                            myIntent = new Intent(getApplicationContext(), LightActivity.class);
                        }else  if(position ==4){
                            myIntent = new Intent(getApplicationContext(), ProximityActivity.class);
                        }else  if(position ==5){
                            myIntent = new Intent(getApplicationContext(), HeartRateActivity.class);
                        }else  if(position ==6){
                            myIntent = new Intent(getApplicationContext(), PedometerActivity.class);
                        }else  if(position ==7) {
                            myIntent = new Intent(getApplicationContext(), TemperatureActivity.class);
                        }else  if(position ==8) {
                            myIntent = new Intent(getApplicationContext(), HumidityActivity.class);
                        }else  if(position ==9) {
                            myIntent = new Intent(getApplicationContext(), PressureActivity.class);
                        }else  if(position ==10) {
                            myIntent = new Intent(getApplicationContext(), GPSActivity.class);
                        }

                        if(myIntent!=null)
                            startActivity(myIntent);

                    }
                })
        );

    }

    private List<String> fillSensorList() {
        lString = new ArrayList();
        lString.add("Acelerômetro");
        lString.add("Giroscópio");
        lString.add("Magnetômetro");
        lString.add("Luminosidade");
        lString.add("Proximidade");
        lString.add("Batimento Cardíaco");
        lString.add("Pedômetro");
        lString.add("Temperatura Ambiente");
        lString.add("Umidade");
        lString.add("Pressão");
        lString.add("Localização (GPS)");


        return lString;

    }

    private void init() {
        recycleListView = (RecyclerView) findViewById(R.id.deault_recycleListView);
    }

    private void setAdapter(List<String> lSensor) {
        mLayoutManager = new LinearLayoutManager(this);
        recycleListView.setLayoutManager(mLayoutManager);
        recycleListView.setHasFixedSize(true);
        recycleListView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SensorListAdapter(lSensor);
        recycleListView.setAdapter(mAdapter);
    }


}