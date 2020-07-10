package mx.com.character_rick_and_morty.dependecies.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import mx.com.character_rick_and_morty.dependecies.db.model.Character;

@Dao
public abstract class CharacterDao  implements BaseDao<Character>{
    @Query("Select * from character")
    public abstract List<Character> getAllCharacters();
}
