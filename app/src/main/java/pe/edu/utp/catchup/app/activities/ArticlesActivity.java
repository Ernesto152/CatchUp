package pe.edu.utp.catchup.app.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.catchup.R;
import pe.edu.utp.catchup.app.CatchUpApp;
import pe.edu.utp.catchup.app.adapters.ArticlesAdapter;
import pe.edu.utp.catchup.backend.models.Article;
import pe.edu.utp.catchup.backend.models.Source;
import pe.edu.utp.catchup.backend.services.NewsService;

public class ArticlesActivity extends AppCompatActivity {
    RecyclerView articlesRecyclerView;
    RecyclerView.LayoutManager articlesLayoutManager;
    ArticlesAdapter articlesAdapter;
    List<Article> articles;
    final static String TAG = "CatchUp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        articlesRecyclerView = (RecyclerView) findViewById(R.id.articlesRecyclerView);
        articlesLayoutManager = new GridLayoutManager(this, 2);
        articles = new ArrayList<>();
        articlesAdapter = new ArticlesAdapter();
        articlesAdapter.setArticles(articles);
        articlesRecyclerView.setLayoutManager(articlesLayoutManager);
        articlesRecyclerView.setAdapter(articlesAdapter);
        final Source currentSource = CatchUpApp.getInstance().getCurrentSource();
        String newsApiKey = getString(R.string.news_api_key);
        AndroidNetworking.get(NewsService.ARTICLES_URL)
                .addQueryParameter("source", currentSource.getId())
                .addQueryParameter("apiKey", newsApiKey)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equalsIgnoreCase("error")) {
                                Log.d(TAG, response.getString("message"));
                                return;
                            }
                            articles = Article.build(response.getJSONArray("articles"), currentSource);
                            articlesAdapter.setArticles(articles);
                            articlesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getLocalizedMessage());

                    }
                });

    }

}
