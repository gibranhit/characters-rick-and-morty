package mx.com.character_rick_and_morty.dependecies.rest.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import mx.com.character_rick_and_morty.dependecies.rest.response.model.Info;
import mx.com.character_rick_and_morty.dependecies.rest.response.model.Result;

@Data
public class CharacterResponse {
    @SerializedName("info")
    private Info info;
    @SerializedName("results")
    private List<Result> results;
}
