package mx.com.character_rick_and_morty.dependecies.rest.endpoints;

import mx.com.character_rick_and_morty.dependecies.rest.response.CharacterResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiEndPoints {
    @GET
    Call<CharacterResponse> getCharacterByPage(@Url String url);
}
