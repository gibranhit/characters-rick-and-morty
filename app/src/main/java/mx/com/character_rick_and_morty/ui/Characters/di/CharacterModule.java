package mx.com.character_rick_and_morty.ui.Characters.di;

import dagger.Module;
import dagger.Provides;
import mx.com.character_rick_and_morty.dependecies.rest.endpoints.ApiEndPoints;
import mx.com.character_rick_and_morty.ui.Characters.interactor.CharacterInteractor;
import mx.com.character_rick_and_morty.ui.Characters.presenter.CharacterPresenter;
import mx.com.character_rick_and_morty.ui.Characters.view.CharacterContract;

@Module
public class CharacterModule {

    private CharacterContract.View view;

    public CharacterModule(CharacterContract.View view) {
        this.view = view;
    }

    @Provides
    public CharacterContract.View provideView() {
        return view;
    }

    @Provides
    public CharacterContract.Interactor provideInteractor(ApiEndPoints endPoints) {
        return new CharacterInteractor(endPoints);
    }

    @Provides
    public CharacterContract.Presenter providePresenter(CharacterContract.View view, CharacterContract.Interactor interactor) {
        return new CharacterPresenter(view, interactor);
    }
}
