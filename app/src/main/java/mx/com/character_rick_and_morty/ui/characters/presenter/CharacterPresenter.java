package mx.com.character_rick_and_morty.ui.characters.presenter;

import mx.com.character_rick_and_morty.dependecies.rest.callback.ResponseCallBack;
import mx.com.character_rick_and_morty.dependecies.rest.response.CharacterResponse;
import mx.com.character_rick_and_morty.ui.characters.view.CharacterContract;

public class CharacterPresenter implements CharacterContract.Presenter {

    private CharacterContract.View  view;
    private CharacterContract.Interactor interactor;

    public CharacterPresenter(CharacterContract.View view, CharacterContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getCharacterRickAndMorty(String url) {
        view.showDialog();
        interactor.getCharacterRickAndMorty(url, new ResponseCallBack<CharacterResponse>() {
            @Override
            public void onSuccess(CharacterResponse result) {
                view.onSuccessCharacter(result);
                view.hideDialog();
            }

            @Override
            public void onFail(String message) {
                view.onFailCharacter(message);
                view.hideDialog();
            }
        });
    }
}
