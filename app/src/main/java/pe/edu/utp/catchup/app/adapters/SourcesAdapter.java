package pe.edu.utp.catchup.app.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.utp.catchup.R;
import pe.edu.utp.catchup.app.CatchUpApp;
import pe.edu.utp.catchup.app.activities.ArticlesActivity;
import pe.edu.utp.catchup.app.activities.SourcesActivity;
import pe.edu.utp.catchup.backend.models.Source;

/**
 * Created by GrupoUTP on 04/03/2017.
 */

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {
    private List<Source> sources;
    @Override
    public SourcesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_source, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SourcesAdapter.ViewHolder holder, final int position) {
        holder.nameTextView.setText(sources.get(position).getName());
        holder.logoImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.logoImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.logoImageView.setImageUrl(sources.get(position).getUrlToSmallLogo());
        holder.sourceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatchUpApp.getInstance().setCurrentSource(sources.get(position));
                v.getContext().startActivity(new Intent(v.getContext(), ArticlesActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ANImageView logoImageView;
        CardView sourceCardView;
        public ViewHolder(View itemView) {
            super(itemView);
            sourceCardView = (CardView) itemView.findViewById(R.id.sourceCardView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            logoImageView = (ANImageView) itemView.findViewById(R.id.logoImageView);
        }
    }
}
