package bit01.com.mx.echale.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import bit01.com.mx.echale.R;
import bit01.com.mx.echale.models.Historial;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ericklara on 31/05/17.
 */

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>{

    private List<Historial> apuestas;

    private Context context;

    public HistorialAdapter(List<Historial> apuestas) {
        this.apuestas = apuestas;
    }

    @Override
    public HistorialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_historial, parent, false);

        HistorialViewHolder viewHolder = new HistorialViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HistorialViewHolder holder, int position) {
        Historial promocion = apuestas.get(position);
        holder.bindHistorial(promocion, position+1);
    }

    @Override
    public int getItemCount() {
        return apuestas.size();
    }

    public class HistorialViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.historial_local_team_image)
        CircleImageView localTeamImage;

        @BindView(R.id.historial_away_team_image)
        CircleImageView awayTeamImage;

        @BindView(R.id.historial_local_team_name)
        TextView localTeamName;

        @BindView(R.id.historial_away_team_name)
        TextView awayTeamName;

        @BindView(R.id.historial_match_date)
        TextView matchDate;

        @BindView(R.id.historial_evento)
        TextView evento;

        @BindView(R.id.historia_apuesta)
        TextView apuestaNumero;

        @BindView(R.id.historial_monto)
        TextView monto;

        View rootView;

        public HistorialViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindHistorial(Historial historial, int numeroApuesta){
            localTeamName.setText(historial.getNombreLocal());
            awayTeamName.setText(historial.getNombreVisita());
            matchDate.setText(historial.getFecha());
            evento.setText("Evento: " + historial.getEvento());
            monto.setText("Monto: " + historial.getMonto());
            apuestaNumero.setText("Apuesta #" + numeroApuesta);

            if (!historial.getUriLocal().isEmpty()) {

                Glide.with(context)
                        .load(historial.getUriLocal())
                        .into(localTeamImage);


            }

            if (!historial.getUriVisita().isEmpty()) {

                Glide.with(context)
                        .load(historial.getUriVisita())
                        .into(awayTeamImage);
            }
        }
    }

}
