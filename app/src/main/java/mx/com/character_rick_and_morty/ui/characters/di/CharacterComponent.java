package mx.com.character_rick_and_morty.ui.characters.di;

import dagger.Component;
import mx.com.character_rick_and_morty.dependecies.di.CustomScope;
import mx.com.character_rick_and_morty.dependecies.di.AppComponent;
import mx.com.character_rick_and_morty.ui.characters.base.CharacterBaseFragment;

@CustomScope
@Component(dependencies = AppComponent.class, modules = CharacterModule.class)
public interface CharacterComponent {
    void inject(CharacterBaseFragment ft);
}
