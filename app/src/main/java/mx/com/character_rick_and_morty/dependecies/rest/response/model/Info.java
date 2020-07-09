package mx.com.character_rick_and_morty.dependecies.rest.response.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Info {
    @SerializedName("next")
    private String next;
    @SerializedName("prev")
    private String prev;
}
