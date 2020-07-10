package mx.com.character_rick_and_morty.ui.characters.ft;

import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import mx.com.character_rick_and_morty.R;
import mx.com.character_rick_and_morty.dependecies.db.model.Character;
import mx.com.character_rick_and_morty.dependecies.rest.response.CharacterResponse;
import mx.com.character_rick_and_morty.dependecies.rest.response.model.Info;
import mx.com.character_rick_and_morty.dependecies.rest.response.model.Result;
import mx.com.character_rick_and_morty.ui.characters.adapter.CharacterAdapter;
import mx.com.character_rick_and_morty.ui.characters.base.CharacterBaseFragment;

import static mx.com.character_rick_and_morty.dependecies.rest.endpoints.ApiConstants.BASE_URL;
import static mx.com.character_rick_and_morty.dependecies.rest.endpoints.ApiConstants.CHARACTER;
import static mx.com.character_rick_and_morty.ui.characters.adapter.CharacterAdapter.CHARACTER_TYPE;

public class CharacterFragment extends CharacterBaseFragment {

    @BindView(R.id.rv_characters)
    protected RecyclerView rvCharacters;

    @BindView(R.id.chip_group_gender)
    protected ChipGroup chipGroupGender;

    @BindView(R.id.chip_group_specie)
    protected ChipGroup chipGroupSpecie;

    @BindView(R.id.chip_group_status)
    protected ChipGroup chipGroupStatus;

    @BindView(R.id.btn_back)
    protected TextView btnBack;

    @BindView(R.id.btn_forward)
    protected TextView btnForward;

    @BindView(R.id.et_search_character)
    protected EditText etSearchCharacter;

    private Info info;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_character;
    }

    @OnEditorAction(R.id.et_search_character)
    protected boolean searchCharacter(int action) {
        if (!etSearchCharacter.getText().toString().isEmpty()){
            if (action == EditorInfo.IME_ACTION_SEARCH){
                String query = "&" + getTag(chipGroupSpecie) +"&"+ getTag(chipGroupStatus) +"&"+ getTag(chipGroupGender);
                String character = "name="+ etSearchCharacter.getText().toString();
                getPresenter().getCharacterRickAndMorty(BASE_URL + CHARACTER + character+query);
            }
        } else {
                Toast.makeText(activity, getString(R.string.insert_character_name), Toast.LENGTH_SHORT).show();
            }
        return true;
    }

    private String getTag(ChipGroup chipGroup) {
        String tag = "";
        for (int i = 0;  i < chipGroup.getChildCount(); i++){
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if (chip.isChecked()){
                tag = chip.getTag().toString();
            }
        }
        return tag;
    }

    @Override
    public void onSuccessCharacter(CharacterResponse response) {
        setUpListCharacter(response.getResults());
        btnBack.setEnabled(response.getInfo().getPrev() != null);
        btnForward.setEnabled(response.getInfo().getNext() != null);
        info = response.getInfo();
    }

    @Override
    public void onFailCharacter(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    private void setUpListCharacter(List<Result> results){
        List<Character> characters = getListCharacter(results);
        getCharacterDao().insertAll(characters);
        CharacterAdapter adapter = new CharacterAdapter(activity);
        adapter.setResults(results);
        adapter.setType(CHARACTER_TYPE);
        rvCharacters.setAdapter(adapter);
        rvCharacters.setLayoutManager(new LinearLayoutManager(activity));
        rvCharacters.setHasFixedSize(true);
    }

    private List<Character> getListCharacter(List<Result> results){
        List<Character> characters = new ArrayList<>();
        for (Result result : results){
            characters.add(Character.parseCharacter(result));
        }
        return characters;
    }

    @OnClick(R.id.btn_back)
    protected void previousPage(){
        getPresenter().getCharacterRickAndMorty(info.getPrev());
    }

    @OnClick(R.id.btn_forward)
    protected void nextPage(){
        getPresenter().getCharacterRickAndMorty(info.getNext());
    }
}