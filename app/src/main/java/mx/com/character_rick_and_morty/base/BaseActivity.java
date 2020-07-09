package mx.com.character_rick_and_morty.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import mx.com.character_rick_and_morty.dependecies.di.App;
import mx.com.character_rick_and_morty.dependecies.di.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    private AppComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        injectDependencies();
        injectViews();
        initUI();
    }

    /**
     * init specific configuration about the view.
     */
    protected void initUI(){
        //Default implementation to avoid mandatory
    }

    /**
     * @return The layout that's gonna be the activity view.
     */
    protected abstract int getLayout();

    protected void setUpComponent(AppComponent appComponent) {
        //Default implementation to avoid mandatory
    }

    /**
     * Every object annotated with {@link butterknife.Bind} its gonna injected trough butterknife
     */
    private void injectViews() {
        ButterKnife.bind(this);
    }

    /**
     * Setup the object graph and inject the dependencies needed on this activity.
     */
    private void injectDependencies() {
        component = App.getApp(this).getComponent();
        setUpComponent(component);
    }
}
