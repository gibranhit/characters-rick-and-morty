package mx.com.character_rick_and_morty.dependecies.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mx.com.character_rick_and_morty.dependecies.rest.response.model.Result;

@Entity(tableName = "character")
@Data
@EqualsAndHashCode(callSuper = false)
public class Character {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "status")
    private String status;
    @ColumnInfo(name = "species")
    private String species;
    @ColumnInfo(name = "image")
    private String image;
    @ColumnInfo(name = "origin")
    private String origin;
    @ColumnInfo(name = "location")
    private String location;

    @Ignore
    public static Character parseCharacter(Result result){
        Character character = new Character();
        character.setImage(result.getImage());
        character.setLocation(result.getLocation().getName());
        character.setOrigin(result.getOrigin().getName());
        character.setSpecies(result.getSpecies());
        character.setName(result.getName());
        character.setStatus(result.getStatus());
        return character;
    }
}
