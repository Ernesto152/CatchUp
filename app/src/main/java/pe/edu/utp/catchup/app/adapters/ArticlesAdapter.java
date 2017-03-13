package pe.edu.utp.catchup.app.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.utp.catchup.R;
import pe.edu.utp.catchup.app.CatchUpApp;
import pe.edu.utp.catchup.app.activities.ArticleActivity;
import pe.edu.utp.catchup.backend.models.Article;

/**
 * Created by GrupoUTP on 10/03/2017.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private List<Article> articles;

    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_article, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ViewHolder holder, final int position) {
        holder.pictureImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.pictureImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.pictureImageView.setImageUrl(articles.get(position).getUrlToImage());
        holder.titleTextView.setText(articles.get(position).getTitle());
        holder.articleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatchUpApp.getInstance().setCurrentArticle(articles.get(position));
                v.getContext().startActivity(new Intent(v.getContext(), ArticleActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ANImageView pictureImageView;
        TextView titleTextView;
        CardView articleCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            pictureImageView = (ANImageView) itemView.findViewById(R.id.pictureImageView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            articleCardView = (CardView) itemView.findViewById(R.id.articleCardView);
        }
    }
}
