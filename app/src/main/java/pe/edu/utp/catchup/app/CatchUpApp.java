package pe.edu.utp.catchup.app;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import pe.edu.utp.catchup.backend.models.Article;
import pe.edu.utp.catchup.backend.models.Source;
import pe.edu.utp.catchup.backend.services.NewsService;


/**
 * Created by GrupoUTP on 04/03/2017.
 */

public class CatchUpApp extends Application {
    // Instance for Singleton Pattern
    static CatchUpApp catchUpApp;
    NewsService newsService = new NewsService();

    public CatchUpApp() {
        super();
        catchUpApp = this;
    }
    // Singleton Access
    public static CatchUpApp getInstance() {
        return catchUpApp;
    }

    public Source getCurrentSource() { return newsService.getCurrentSource();}

    public void setCurrentSource(Source source) {
        newsService.setCurrentSource(source);
    }
    public Article getCurrentArticle() { return newsService.getCurrentArticle();}

    public void setCurrentArticle(Article article) {
        newsService.setCurrentArticle(article);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
