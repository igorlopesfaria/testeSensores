package matd02.ufba.br.testesensores.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import matd02.ufba.br.testesensores.R;

/**
 * Created by Igor Lopes de Faria on 17/01/2016.
 */
public class SensorListAdapter extends RecyclerView.Adapter<SensorListAdapter.ViewHolder> {

    private List<String> lSensor;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iconIMG;
        public TextView titleTX;
        public RelativeLayout mainLayout;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);
            titleTX = (TextView) itemView
                    .findViewById(R.id.itemTXV);
        }
    }

    public SensorListAdapter(List<String> lSensor) {
        this.lSensor = lSensor;
    }

    @Override
    public SensorListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder vhItem = new ViewHolder(v, viewType);
        return vhItem;

    }



    @Override
    public void onBindViewHolder(final SensorListAdapter.ViewHolder holder, int position) {
        String item = lSensor.get(position);
        holder.titleTX.setText(item);


    }

    @Override
    public int getItemCount() {
        return lSensor.size();
    }

}
