package mx.com.character_rick_and_morty.dependecies.di;

import android.app.Application;
import android.content.Context;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import mx.com.character_rick_and_morty.R;
import mx.com.character_rick_and_morty.dependecies.rest.RestModule;
import mx.com.character_rick_and_morty.dependecies.rest.endpoints.ApiConstants;


public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
        setUpAppCenter();
    }

    /**
     * The object graph contains all the instances of the objects
     * that resolve a dependency
     * */
    private void setupGraph() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .restModule(new RestModule(this, ApiConstants.BASE_URL))
                .build();
    }

    private void setUpAppCenter(){
        AppCenter.start(this, getString(R.string.app_key),
                Analytics.class, Crashes.class);
    }

    public AppComponent getComponent() {

        if(component == null){
            setupGraph();
        }

        return component;
    }

    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }
}
