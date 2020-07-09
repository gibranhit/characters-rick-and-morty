package mx.com.character_rick_and_morty.dependecies.rest.response.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Origin {
    @SerializedName("name")
    private String name;
}
