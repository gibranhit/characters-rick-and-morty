package mx.com.character_rick_and_morty.dependecies.rest.response.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Result {
    @SerializedName("name")
    private String name;
    @SerializedName("status")
    private String status;
    @SerializedName("species")
    private String species;
    @SerializedName("image")
    private String image;
    @SerializedName("origin")
    private Origin origin;
    @SerializedName("location")
    private Origin location;
}
