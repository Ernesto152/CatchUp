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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.catchup.R;
import pe.edu.utp.catchup.app.adapters.SourcesAdapter;
import pe.edu.utp.catchup.backend.models.Source;
import pe.edu.utp.catchup.backend.services.NewsService;

public class SourcesActivity extends AppCompatActivity {
    List<Source> sources;
    RecyclerView sourcesRecyclerView;
    RecyclerView.LayoutManager sourcesLayoutManager;
    SourcesAdapter sourcesAdapter;
    final static String TAG = "CatchUp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sources = new ArrayList<>();
        // TODO: Implement RESTful service request
        AndroidNetworking.get(NewsService.SOURCES_URL)
                .addQueryParameter("language", "en")
                .setTag("CatchUp")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("status");
                            if(status.equalsIgnoreCase("error")) {
                                String message = response.getString("message");
                                Log.d(TAG, message);
                                return;
                            }
                            JSONArray jsonSources = response.getJSONArray("sources");
                            int sourcesCount = jsonSources.length();
                            Log.d(TAG, "Sources Count = " + String.valueOf(sourcesCount));
                            List<Source> sources = Source.build(jsonSources);
                            sourcesAdapter.setSources(sources);
                            sourcesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "Exception while requesting Sources");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getLocalizedMessage());
                    }
                });

        sourcesLayoutManager = new GridLayoutManager(this, 2);
        sourcesAdapter = new SourcesAdapter();
        sourcesAdapter.setSources(sources);
        sourcesRecyclerView = (RecyclerView) findViewById(R.id.sourcesRecyclerView);
        sourcesRecyclerView.setLayoutManager(sourcesLayoutManager);
        sourcesRecyclerView.setAdapter(sourcesAdapter);
    }



}
