package mx.com.character_rick_and_morty.dependecies.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import mx.com.character_rick_and_morty.dependecies.db.dao.CharacterDao;
import mx.com.character_rick_and_morty.dependecies.db.model.Character;


@Database(entities = {Character.class}, version = 1, exportSchema = false)
public abstract class CharacterDB  extends RoomDatabase {

    public abstract CharacterDao getCharacterDao();

    public static final String DATABASE_NAME = "rick_and_morty_db";
}
