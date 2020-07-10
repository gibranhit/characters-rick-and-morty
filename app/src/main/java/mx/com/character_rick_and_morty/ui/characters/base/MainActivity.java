package mx.com.character_rick_and_morty.ui.characters.base;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import mx.com.character_rick_and_morty.R;
import mx.com.character_rick_and_morty.base.BaseActivity;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_nav_view_dashboard)
    protected BottomNavigationView bottomNavDashboard;

    private NavController controller;

    private NavOptions.Builder builder;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI() {
        setUpToolbar();
        setUpNavigation();
    }

    private void setUpToolbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    private void setUpNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_dashboard);
        controller = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavDashboard, controller);
        bottomNavDashboard.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (isValidatedDestination(item.getItemId())) {
            controller.navigate(item.getItemId());
        }
        return true;
    }

    private boolean isValidatedDestination(int destination) {
        return destination != controller.getCurrentDestination().getId();
    }
}