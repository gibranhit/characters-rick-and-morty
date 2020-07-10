package mx.com.character_rick_and_morty.dependecies.di;


import javax.inject.Singleton;

import dagger.Component;
import mx.com.character_rick_and_morty.dependecies.db.dao.CharacterDao;
import mx.com.character_rick_and_morty.dependecies.rest.RestModule;
import mx.com.character_rick_and_morty.dependecies.rest.endpoints.ApiEndPoints;

@Singleton
@Component(modules = {AppModule.class, RestModule.class})
public interface AppComponent {

    ApiEndPoints getApiService();

    CharacterDao getCharacterDao();
}
