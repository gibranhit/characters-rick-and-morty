package mx.com.character_rick_and_morty.ui.characters.ft;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import mx.com.character_rick_and_morty.R;
import mx.com.character_rick_and_morty.dependecies.db.model.Character;
import mx.com.character_rick_and_morty.ui.characters.adapter.CharacterAdapter;
import mx.com.character_rick_and_morty.ui.characters.base.CharacterBaseFragment;

import static mx.com.character_rick_and_morty.ui.characters.adapter.CharacterAdapter.HISTORY_TYPE;

public class SearchFragment extends CharacterBaseFragment {

    @BindView(R.id.rv_characters)
    protected RecyclerView rvCharacters;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initUI() {
        setUpListHistory();
    }

    private void setUpListHistory() {
        List<Character> characters = getCharacterDao().getAllCharacters();
        CharacterAdapter adapter = new CharacterAdapter(activity);
        adapter.setCharacters(characters);
        adapter.setType(HISTORY_TYPE);
        rvCharacters.setAdapter(adapter);
        rvCharacters.setLayoutManager(new LinearLayoutManager(activity));
        rvCharacters.setHasFixedSize(true);
    }
}