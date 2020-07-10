package mx.com.character_rick_and_morty.ui.characters.base;

import android.app.Dialog;

import javax.inject.Inject;

import lombok.Getter;
import mx.com.character_rick_and_morty.base.BaseFragment;
import mx.com.character_rick_and_morty.dependecies.db.dao.CharacterDao;
import mx.com.character_rick_and_morty.dependecies.di.AppComponent;
import mx.com.character_rick_and_morty.dependecies.rest.response.CharacterResponse;
import mx.com.character_rick_and_morty.ui.characters.di.CharacterModule;
import mx.com.character_rick_and_morty.ui.characters.di.DaggerCharacterComponent;
import mx.com.character_rick_and_morty.ui.characters.view.CharacterContract;

import static mx.com.character_rick_and_morty.utils.AndroidUtils.configProgressBar;

public abstract class CharacterBaseFragment extends BaseFragment implements CharacterContract.View {

    @Getter
    @Inject
    CharacterContract.Presenter presenter;

    @Getter
    @Inject
    CharacterDao characterDao;

    private Dialog dialog;

    @Override
    protected void setUpComponent(AppComponent appComponent) {
        DaggerCharacterComponent.builder()
                .appComponent(appComponent)
                .characterModule(new CharacterModule(this))
                .build().inject(this);
    }

    private void setUpProgressBar() {
        if (dialog == null)
            dialog = configProgressBar(activity);
    }

    @Override
    public void onSuccessCharacter(CharacterResponse response) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onFailCharacter(String message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showDialog() {
        setUpProgressBar();
        dialog.show();
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();

    }
}
