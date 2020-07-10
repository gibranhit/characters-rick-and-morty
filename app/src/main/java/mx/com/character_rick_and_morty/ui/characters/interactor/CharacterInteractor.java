package mx.com.character_rick_and_morty.ui.characters.interactor;

import mx.com.character_rick_and_morty.dependecies.rest.callback.ResponseCallBack;
import mx.com.character_rick_and_morty.dependecies.rest.endpoints.ApiEndPoints;
import mx.com.character_rick_and_morty.dependecies.rest.response.CharacterResponse;
import mx.com.character_rick_and_morty.ui.characters.view.CharacterContract;

public class CharacterInteractor implements CharacterContract.Interactor {

    private ApiEndPoints endPoints;

    public CharacterInteractor(ApiEndPoints endPoints) {
        this.endPoints = endPoints;
    }

    @Override
    public void getCharacterRickAndMorty(String url, ResponseCallBack<CharacterResponse> callBack) {
        endPoints.getCharacterByPage(url).enqueue(callBack);
    }
}
