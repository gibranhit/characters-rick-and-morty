package mx.com.character_rick_and_morty.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import mx.com.character_rick_and_morty.dependecies.di.App;
import mx.com.character_rick_and_morty.dependecies.di.AppComponent;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        injectDependencies();
        initUI();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) getActivity();
    }

    /**
     * init specific configuration about the view.
     */
    protected abstract void initUI();

    /**
     * Specify the layout of the fragment to be inflated in the {@link BaseFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    protected abstract int getFragmentLayout();

    private void injectDependencies() {
        setUpComponent(App.getApp(getActivity()).getComponent());
    }

    /**
     * Replace all the annotated fields with ButterKnife annotations with the proper value
     */
    private void bindViews(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    /**
     * This method will setup the injector and will commit the dependencies injections.
     */
    protected void setUpComponent(AppComponent appComponent) {
        //Default implementation to avoid mandatory
    }
}
