package mx.com.character_rick_and_morty.ui.characters.view;

import mx.com.character_rick_and_morty.dependecies.rest.callback.ResponseCallBack;
import mx.com.character_rick_and_morty.dependecies.rest.response.CharacterResponse;

public interface CharacterContract {

    interface View{
        void onSuccessCharacter(CharacterResponse response);
        void onFailCharacter(String message);
        void showDialog();
        void hideDialog();
    }

    interface Presenter{
        void getCharacterRickAndMorty(String url);
    }

    interface Interactor{
        void getCharacterRickAndMorty(String url, ResponseCallBack<CharacterResponse> callBack);
    }
}
